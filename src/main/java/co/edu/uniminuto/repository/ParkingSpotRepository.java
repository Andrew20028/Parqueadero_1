package co.edu.uniminuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.uniminuto.model.ParkingSpot;

import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    
    // Método para encontrar espacios libres
    @Query("SELECT p FROM ParkingSpot p WHERE p.occupied = false")
    List<ParkingSpot> findFreeSpots();
    
    // Método para encontrar espacios ocupados
    @Query("SELECT p FROM ParkingSpot p WHERE p.occupied = true")
    List<ParkingSpot> findOccupiedSpots();
}
