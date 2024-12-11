package co.edu.uniminuto.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle") // Especifica la tabla en la base de datos
public class Vehicle {

    @Id
    private String plateNumber; // Placa del vehículo

    private String owner; // Propietario del vehículo
    private String cedula; // Cédula del propietario
    private String telefono; // Número telefónico
    private String house; // Casa a la que se dirige
    private String assignedSpace;

    private LocalDateTime entryTime; // Hora de entrada al parqueadero
    private LocalDateTime exitTime; // Hora de salida del parqueadero

    private String id;
  
    @OneToOne
    @JoinColumn(name = "parking_spot_id") // Clave foránea que enlaza con ParkingSpot
    private ParkingSpot parkingSpot; // Espacio de parqueo asociado al vehículo

    // Getters y setters
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

	public String getAssignedSpace() {
		return assignedSpace;
	}

	public void setAssignedSpace(String assignedSpace) {
		this.assignedSpace = assignedSpace;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
} 
