package com.repowr.airbnb.repository;

import com.repowr.airbnb.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{

}
