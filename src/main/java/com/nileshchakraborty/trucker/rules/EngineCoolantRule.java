package com.nileshchakraborty.trucker.rules;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.core.BasicRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nileshchakraborty.trucker.entity.Alert;
import com.nileshchakraborty.trucker.entity.Reading;
import com.nileshchakraborty.trucker.entity.Vehicle;
import com.nileshchakraborty.trucker.repository.AlertRepository;
import com.nileshchakraborty.trucker.repository.VehicleRepository;
@Rule(name = "Engine Coolant Low / Engine Light On", description = "If engine coolant low || engine light on")
@Component
public class EngineCoolantRule extends BasicRule {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AlertRepository alertRepository;

    @Condition
    public boolean condition(@Fact("enginecoolant") Reading reading) {

        Optional<Vehicle> existing = vehicleRepository.findByVin(reading.getVin());
        return existing.filter(vehicle -> reading.isCheckEngineLightOn() || reading.isEngineCoolantLow()).isPresent();
    }

    @Action
    public void action(@Fact("enginecoolant") Reading reading) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss.s");
        Alert alert = new Alert();
        alert.setVin(reading.getVin());
        alert.setPriority("LOW");
        alert.setDescription("Engine Coolant Low / Engine Light On");
        alert.setTimestamp(date);
        alertRepository.save(alert);
    }
}
