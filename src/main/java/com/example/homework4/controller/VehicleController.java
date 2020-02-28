package com.example.homework4.controller;


import com.example.homework4.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.homework4.service.VehicleService;

import java.util.List;


@Controller
@Primary

public class VehicleController {

    private VehicleService vehicleService;
    private List<Vehicle> vehicleList;


    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
        this.vehicleList = vehicleService.getVehiclesList();


    }

    @GetMapping("/vehicle")
    public String getVehicle(Model model) {
        model.addAttribute("vehicles", vehicleList);
        model.addAttribute("request", new Vehicle());
        return "vehicle";
    }

    @GetMapping("/reset-list")
    public String resetVehicleList() {
        vehicleService.clearVehicleList();
        vehicleService.initVehicleList();
        return "redirect:/vehicle";
    }


    @GetMapping("/search-id")
    public String getById(@RequestParam long id) {
        Vehicle vehicle = vehicleService.getId(id);
        vehicleService.clearVehicleList();
        vehicleService.getVehiclesList().add(vehicle);
        return "redirect:/vehicle";
    }


    @PostMapping("/add-vehicle")
    public String addVehicle(@ModelAttribute Vehicle newVehicle) {
        vehicleService.getVehiclesList().add(newVehicle);
        return "redirect:/vehicle";
    }

    @PostMapping("/remove-vehicle")
    public String removeVehicle(@RequestParam long id) {
        vehicleService.removeVehicle(id);
        return "redirect:/vehicle";
    }

    @PostMapping("/change-color")
    public String changeColor(@RequestParam long id, String color) {
        vehicleService.changeColor(color, id);
        return "redirect:/vehicle";
    }

    @PostMapping("/change-all")
    public String changeAllParamVehicle(@ModelAttribute Vehicle newVehicle) {
        vehicleService.changeAllParamOfVehicle(newVehicle);
        return "redirect:/vehicle";
    }


}

