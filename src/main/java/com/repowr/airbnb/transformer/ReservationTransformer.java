package com.repowr.airbnb.transformer;

import com.repowr.airbnb.dto.response.Customer;
import com.repowr.airbnb.dto.response.Day;
import com.repowr.airbnb.dto.response.Room;
import com.repowr.airbnb.entity.BookingEntity;
import com.repowr.airbnb.entity.CustomerEntity;
import com.repowr.airbnb.entity.RoomEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ReservationTransformer {

    public Room roomEntityToRoomDto(RoomEntity roomEntity) {
        Room room = new Room(roomEntity.getId(), roomEntity.getName(), null, roomEntity.getAddress(),
                roomEntity.getCity(), roomEntity.getState(), null);
        if(roomEntity.getCustomerEntity() != null) {
            CustomerEntity customerEntity = roomEntity.getCustomerEntity();
            Customer customer = new Customer(customerEntity.getId(), customerEntity.getEmail(),
                    customerEntity.getFirstName(), customerEntity.getLastName());
            room.setOwner(customer);
        }
        if(!roomEntity.getBookings().isEmpty()) {
            getBookedDays(roomEntity.getBookings());
        }
        return room;
    }

    private List<Day> getBookedDays(Set<BookingEntity> bookingEntities) {
        List<Day> dayList = new ArrayList<>();
        // I realize this is theoretically O(n^2), but there's only so many days, so it's really not
        bookingEntities.forEach(bookingEntity -> {
            LocalDate startDate = bookingEntity.getStartDate();
            long daysBetween = ChronoUnit.DAYS.between(startDate, bookingEntity.getEndDate());
            for(int i = 0; i < daysBetween; i++) {
                // granted any day appearing in this list is booked, this is just in case things get unbooked
                Day day = new Day();
                //todo this may be one off
                day.setDay(startDate.plusDays(i));
                day.setBooked(true);
                dayList.add(day);
            }
        });
        return dayList;
    }
}
