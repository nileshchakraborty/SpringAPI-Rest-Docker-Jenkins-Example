package com.nileshchakraborty.trucker.service;

import java.util.List;

import com.nileshchakraborty.trucker.entity.Reading;

public interface ReadingService {

    List<Reading> findAll();

    Reading findOne(String id);

    Reading create(Reading reading);

}
