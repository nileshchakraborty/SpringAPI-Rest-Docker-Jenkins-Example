package com.nileshchakraborty.trucker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nileshchakraborty.trucker.entity.Vehicle;
import com.nileshchakraborty.trucker.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = {"http://mocker.egen.io", "http://mocker.ennate.academy"})
public class VehicleController {

    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> findAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{vin}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Vehicle findOne(@PathVariable String vin) {
        return service.findOne(vin);
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> update(@RequestBody List<Vehicle> vList) {
        System.out.println("Receiving vehicles list with size:- "+vList.size());
        return (List<Vehicle>) service.update(vList);
    }

}
