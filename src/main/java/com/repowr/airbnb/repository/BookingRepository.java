package com.repowr.airbnb.repository;

import com.repowr.airbnb.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
}
