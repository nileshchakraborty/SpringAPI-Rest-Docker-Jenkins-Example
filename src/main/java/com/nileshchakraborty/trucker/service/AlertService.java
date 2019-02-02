package com.nileshchakraborty.trucker.service;

import java.util.List;

import com.nileshchakraborty.trucker.entity.Alert;

public interface AlertService {

    List<Alert> findAlertsByPriority(String priority);

    List<Alert> findAlertsByVin(String vin);

    Alert create(Alert alert);
}
