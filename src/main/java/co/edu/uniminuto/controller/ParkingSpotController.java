package co.edu.uniminuto.controller;

import co.edu.uniminuto.model.ParkingSpot;
import co.edu.uniminuto.model.Vehicle;
import co.edu.uniminuto.repository.ParkingSpotRepository;
import co.edu.uniminuto.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/parking-spots")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    // Mostrar todos los espacios de parqueo
    @GetMapping
    public String getAllSpots(Model model) {
        List<ParkingSpot> parkingSpots = parkingSpotRepository.findAll();
        model.addAttribute("parkingSpots", parkingSpots); // Asegúrate de que esto sea correcto
        
        List<Vehicle> vehicles = vehicleRepository.findAll(); // Recibe los vehículos
        model.addAttribute("vehicles", vehicles); // Asegúrate de que esto se pase correctamente
        
        return "parkingspots"; // Asegúrate de que esta sea la vista correcta
    }

    @PostMapping("/register")
    @ResponseBody // Esto hace que la respuesta sea JSON y no HTML
    public ResponseEntity<?> registerVehicle(@RequestBody Vehicle vehicle) {
        if (vehicle.getPlateNumber() == null || vehicle.getPlateNumber().isEmpty() || 
            vehicle.getOwner() == null || vehicle.getOwner().isEmpty() || 
            vehicle.getCedula() == null || vehicle.getCedula().isEmpty() || 
            vehicle.getTelefono() == null || vehicle.getTelefono().isEmpty() || 
            vehicle.getHouse() == null || vehicle.getHouse().isEmpty()) {
        	vehicle.setEntryTime(LocalDateTime.now());  // Captura la hora de entrada
            Vehicle savedVehicle = vehicleRepository.save(vehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
        
        }

        try {
            vehicleRepository.save(vehicle);
            return ResponseEntity.ok("Vehículo registrado exitosamente: " + vehicle.getPlateNumber());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar el vehículo.");
        }
    }

    // Endpoint para obtener la lista de vehículos registrados
    @GetMapping("/list")
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Ver espacios libres
    @GetMapping("/free")
    public String getFreeSpots(Model model) {
        List<ParkingSpot> freeSpots = parkingSpotRepository.findFreeSpots();
        model.addAttribute("freeSpots", freeSpots);
        return "freeParkingSpots";
    }

    // Ver espacios ocupados
    @GetMapping("/occupied")
    public String getOccupiedSpots(Model model) {
        List<ParkingSpot> occupiedSpots = parkingSpotRepository.findOccupiedSpots();
        model.addAttribute("occupiedSpots", occupiedSpots);
        return "occupiedParkingSpots";
    }

    // Asignar un espacio de parqueo a un vehículo
    @PostMapping("/assign/{plateNumber}")
    public String assignSpotToVehicle(@PathVariable String plateNumber, Model model) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(plateNumber);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();

            // Verificar si el vehículo ya tiene un espacio asignado
            if (vehicle.getParkingSpot() != null) {
                model.addAttribute("error", "El vehículo ya tiene un espacio asignado.");
                return "parkingspots";  // Volver a la misma página sin redirigir
            }

            // Buscar un espacio libre
            List<ParkingSpot> freeSpots = parkingSpotRepository.findFreeSpots();
            if (freeSpots.isEmpty()) {
                model.addAttribute("error", "No hay espacios disponibles.");
                return "freeParkingSpots";  // Volver a la vista de espacios libres
            }

            // Asignar el primer espacio libre
            ParkingSpot assignedSpot = freeSpots.get(0);
            assignedSpot.setOccupied(true);  // Marcar el espacio como ocupado
            parkingSpotRepository.save(assignedSpot);

            vehicle.setParkingSpot(assignedSpot);  // Asocia el espacio con el vehículo
            vehicleRepository.save(vehicle);

            model.addAttribute("message", "Espacio asignado exitosamente: " + assignedSpot.getSpotNumber());

            return "parkingspots";  // Volver a la misma vista de los espacios de parqueo
        } else {
            model.addAttribute("error", "Vehículo no encontrado.");
            return "parkingspots";  // Volver a la misma página si no se encuentra el vehículo
        }
    }

   
    // Dar salida a un vehículo (liberar el espacio de parqueo)
    @PostMapping("/exit/{plateNumber}")
    public String exitVehicle(@PathVariable String plateNumber, Model model) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(plateNumber);
        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();

            // Verificar si el vehículo tiene un espacio asignado
            if (vehicle.getParkingSpot() != null) {
                ParkingSpot parkingSpot = vehicle.getParkingSpot();
                parkingSpot.setOccupied(false);  // Marcar el espacio como libre
                parkingSpotRepository.save(parkingSpot);

                vehicle.setParkingSpot(null);  // Desasociar el espacio del vehículo
                vehicleRepository.save(vehicle);

                model.addAttribute("message", "Vehículo dado de salida exitosamente: " + plateNumber);
            } else {
                model.addAttribute("error", "El vehículo no tiene espacio asignado.");
            }

            return "parkingspots";  // Volver a la lista de espacios de parqueo
        } else {
            model.addAttribute("error", "Vehículo no encontrado.");
            return "parkingspots";  // Volver a la misma página si no se encuentra el vehículo
        }
    }
    
}