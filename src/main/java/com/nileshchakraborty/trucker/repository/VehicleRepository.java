package com.nileshchakraborty.trucker.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nileshchakraborty.trucker.entity.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {

    Optional<Vehicle> findByVin(String vin);
}
