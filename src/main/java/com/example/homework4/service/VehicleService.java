package com.example.homework4.service;

import com.example.homework4.model.Vehicle;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class VehicleService {

    private List<Vehicle> vehiclesList;


    public VehicleService() {
        this.vehiclesList = new ArrayList<>();
        initVehicleList();

    }

    public List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }

    public List<Vehicle> initVehicleList() {
        vehiclesList.add(new Vehicle(1, "Audi", "A4", "black"));
        vehiclesList.add(new Vehicle(2, "BMW", "3", "red"));
        vehiclesList.add(new Vehicle(3, "Honda", "Accord", "red"));
        return vehiclesList;
    }

    public List<Vehicle> clearVehicleList() {
        vehiclesList.clear();
        return vehiclesList;
    }


    public Vehicle getId(long id) {
        Vehicle vehicleById = vehiclesList.stream().filter(vehicle -> vehicle.getId() == id).findFirst().get();
        return vehicleById;
    }

    public List<Vehicle> removeVehicle(long id) {
        Vehicle vehicleToRemove = vehiclesList.stream().filter(vehicle -> vehicle.getId() == id).findFirst().get();
        vehiclesList.remove(vehicleToRemove);
        return vehiclesList;
    }

    public List<Vehicle> changeColor(String color, long id) {
        getId(id).setColor(color);
        return vehiclesList;
    }

    public List<Vehicle> changeAllParamOfVehicle(Vehicle newVehicle) {
        Optional<Vehicle> first = vehiclesList.stream().filter(vehicle -> vehicle.getId() == newVehicle.getId()).findFirst();
        if (first.isPresent()) {
            first.get().setModel(newVehicle.getModel());
            first.get().setMark(newVehicle.getMark());
            first.get().setColor(newVehicle.getColor());
        }
        return vehiclesList;


    }


}
