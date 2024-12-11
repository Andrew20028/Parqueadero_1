package co.edu.uniminuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.model.ParkingSpot;
import co.edu.uniminuto.model.Vehicle;
import co.edu.uniminuto.repository.ParkingSpotRepository;
import co.edu.uniminuto.repository.VehicleRepository;

@RestController
@RequestMapping("/admin") // Rutas base aún en /admin
public class AdminController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    // Endpoint para obtener todos los vehículos
    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll(); // Retorna todos los vehículos	
    }

    // Obtener todos los espacios de parqueo
    @GetMapping("/spots")
    public List<ParkingSpot> getAllParkingSpots() {
        return parkingSpotRepository.findAll(); // Retorna todos los espacios de parqueo disponibles
    }
}
