package com.nileshchakraborty.trucker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nileshchakraborty.trucker.entity.Alert;

@Repository
public interface AlertRepository extends CrudRepository<Alert, String> {

    Iterable<Alert> findByVin(String vin);

    Iterable<Alert> findByPriority(String priority);
}
