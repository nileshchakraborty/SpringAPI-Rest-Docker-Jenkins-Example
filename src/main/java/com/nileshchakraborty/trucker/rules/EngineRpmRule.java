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

@Rule(name = "High Engine RPM", description = "If engine rpm > redline rpm")
@Component
public class EngineRpmRule extends BasicRule {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private AlertRepository alertRepository;

    @Condition
    public boolean condition(@Fact("enginerpm") Reading reading) {

        Optional<Vehicle> existing = vehicleRepository.findByVin(reading.getVin());
        return existing.filter(vehicle -> reading.getEngineRpm() > vehicle.getRedlineRpm()).isPresent();
    }

    @Action
    public void action(@Fact("enginerpm") Reading reading) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss.s");
        Alert alert = new Alert();
        alert.setVin(reading.getVin());
        alert.setPriority("HIGH");
        alert.setDescription("High Engine RPM");
        alert.setTimestamp(date);
        alertRepository.save(alert);
    }
}
