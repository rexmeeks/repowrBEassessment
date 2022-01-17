package com.repowr.airbnb.repository;

import com.repowr.airbnb.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
}
