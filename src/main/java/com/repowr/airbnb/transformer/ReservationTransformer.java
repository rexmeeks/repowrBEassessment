package com.repowr.airbnb.transformer;

import com.repowr.airbnb.dto.request.CreateRoom;
import com.repowr.airbnb.dto.request.ReserveRoom;
import com.repowr.airbnb.dto.response.Customer;
import com.repowr.airbnb.dto.response.Day;
import com.repowr.airbnb.dto.response.Reservations;
import com.repowr.airbnb.dto.response.Room;
import com.repowr.airbnb.entity.BookingEntity;
import com.repowr.airbnb.entity.CustomerEntity;
import com.repowr.airbnb.entity.RoomEntity;
import com.repowr.airbnb.repository.CustomerRepository;
import com.repowr.airbnb.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
@Slf4j
public class ReservationTransformer {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerTransformer customerTransformer;

    @Autowired
    private RoomRepository roomRepository;

    public Room roomEntityToRoomDto(RoomEntity roomEntity, boolean withBookings) {
        Room room = new Room(roomEntity.getId(), null, roomEntity.getName(), null, roomEntity.getAddress(),
                roomEntity.getCity(), roomEntity.getState(), null);
        if(roomEntity.getCustomerEntity() != null) {
            CustomerEntity customerEntity = roomEntity.getCustomerEntity();
            Customer customer = customerTransformer.customerEntityToCustomerDto(customerEntity);
            room.setOwner(customer);
        }
        if(withBookings && !roomEntity.getBookings().isEmpty()) {
            getBookedDays(roomEntity.getBookings());
        }
        return room;
    }

    public RoomEntity createRoomDtoToRoomEntity(CreateRoom createRoom) {
        RoomEntity roomEntity = new RoomEntity(createRoom.getName(),
                createRoom.getAddress(), createRoom.getCity(), createRoom.getState(), createRoom.getZipCode());
        roomEntity.setCustomerEntity(customerRepository.getById(createRoom.getOwnerId()));
        return roomEntity;
    }

    private List<Day> getBookedDays(List<BookingEntity> bookingEntities) {
        LinkedHashMap<LocalDate, Day> dayLinkedHashMap = new LinkedHashMap<>();
        // I realize this is theoretically O(n^2), but there's only so many days, so it's really not
        // if I had more time I'd come up with a more elegant solution
        bookingEntities.forEach(bookingEntity -> {
            LocalDate startDate = bookingEntity.getStartDate();
            long daysBetween = ChronoUnit.DAYS.between(startDate, bookingEntity.getEndDate());
            for(int i = 0; i <= daysBetween; i++) {
                // granted any day appearing in this list is booked, this is just in case things get unbooked
                Day day = new Day();
                Room room;
                LocalDate computedDay = startDate.plusDays(i);
                Day foundDay = dayLinkedHashMap.get(computedDay);
                if(foundDay != null) {
                    room = roomEntityToRoomDto(bookingEntity.getRoomEntity(), false);
                    room.setBookingCustomer(customerTransformer.customerEntityToCustomerDto(bookingEntity.getCustomerEntity()));
                    foundDay.getRooms().add(room);
                } else {
                    List<Room> roomList = new ArrayList<>();
                    room = roomEntityToRoomDto(bookingEntity.getRoomEntity(), false);
                    room.setBookingCustomer(customerTransformer.customerEntityToCustomerDto(bookingEntity.getCustomerEntity()));
                    roomList.add(room);
                    day.setDay(computedDay);
                    day.setBooked(true);
                    day.setRooms(roomList);
                    dayLinkedHashMap.put(computedDay, day);
                }
            }
        });
        List<Day> dayList = new ArrayList<>(dayLinkedHashMap.values());
        dayList.sort(Comparator.comparing(Day::getDay));
        return dayList;
    }

    public BookingEntity reservationDtoToBookingEntity(Integer roomId, ReserveRoom reserveRoom) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            BookingEntity bookingEntity = new BookingEntity(
                    LocalDate.parse(reserveRoom.getStartDate(), formatter), LocalDate.parse(reserveRoom.getEndDate(), formatter));
            RoomEntity roomEntity = roomRepository.getById(roomId);
            CustomerEntity customerEntity = customerRepository.getById(reserveRoom.getCustId());
            bookingEntity.setRoomEntity(roomEntity);
            bookingEntity.setCustomerEntity(customerEntity);
            return bookingEntity;
        } catch(DateTimeParseException e) {
            log.error("Unable to parse date: {}", e.getMessage());
            return null;
        } catch(EntityNotFoundException e) {
            log.error("Room doesn't exist for id: {} or customer doesn't exist for id: {}, error: {}", roomId,
                    reserveRoom.getCustId(), e.getMessage());
            // ideally would throw back a custom error
            return null;
        }
    }

    public Reservations bookingEntityListToReservations(List<BookingEntity> bookingEntityList) {
        return new Reservations(getBookedDays(bookingEntityList));
    }
}
