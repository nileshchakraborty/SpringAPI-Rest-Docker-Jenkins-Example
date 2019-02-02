package com.nileshchakraborty.trucker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nileshchakraborty.trucker.entity.Reading;

@Repository
public interface ReadingsRepository extends CrudRepository<Reading, String> {

}
