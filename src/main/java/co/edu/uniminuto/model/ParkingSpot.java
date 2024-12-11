package co.edu.uniminuto.model;

import javax.persistence.*;

@Entity
@Table(name = "parking_spot") // Tabla explícita en la base de datos
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-generado
    private Long id;
    private String spotNumber; // Número del espacio de parqueo
    private boolean occupied; // Indica si está ocupado o no

    @OneToOne(mappedBy = "parkingSpot") // Relación inversa con Vehicle
    private Vehicle vehicle; // El vehículo que ocupa este espacio

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

	public void setAvailable(boolean b) {
		// TODO Auto-generated method stub
		
	}
}  