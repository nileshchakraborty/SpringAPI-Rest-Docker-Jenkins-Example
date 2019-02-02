package com.nileshchakraborty.trucker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nileshchakraborty.trucker.entity.Alert;
import com.nileshchakraborty.trucker.service.AlertService;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    private final AlertService service;

    @Autowired
    public AlertController(AlertService alertService) {
        this.service = alertService;
    }

    @GetMapping(path = "/vin/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alert> findAlerts(@PathVariable String vin) {
        return service.findAlertsByVin(vin);
    }

    @GetMapping(path = "/priority/{priority_type}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Alert> findAlertsByPriority(@PathVariable String priority_type) {
        return service.findAlertsByPriority(priority_type);
    }
}
