package co.edu.uniminuto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double vehicleMinuteRate;
    private double vehicleHourlyRate;
    private double vehicleDailyRate;
    private double vehicleWeeklyRate;
    private double vehicleMonthlyRate;

    private double motorcycleMinuteRate;
    private double motorcycleHourlyRate;
    private double motorcycleDailyRate;
    private double motorcycleWeeklyRate;
    private double motorcycleMonthlyRate;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getVehicleMinuteRate() {
        return vehicleMinuteRate;
    }

    public void setVehicleMinuteRate(double vehicleMinuteRate) {
        this.vehicleMinuteRate = vehicleMinuteRate;
    }

    public double getVehicleHourlyRate() {
        return vehicleHourlyRate;
    }

    public void setVehicleHourlyRate(double vehicleHourlyRate) {
        this.vehicleHourlyRate = vehicleHourlyRate;
    }

    public double getVehicleDailyRate() {
        return vehicleDailyRate;
    }

    public void setVehicleDailyRate(double vehicleDailyRate) {
        this.vehicleDailyRate = vehicleDailyRate;
    }

    public double getVehicleWeeklyRate() {
        return vehicleWeeklyRate;
    }

    public void setVehicleWeeklyRate(double vehicleWeeklyRate) {
        this.vehicleWeeklyRate = vehicleWeeklyRate;
    }

    public double getVehicleMonthlyRate() {
        return vehicleMonthlyRate;
    }

    public void setVehicleMonthlyRate(double vehicleMonthlyRate) {
        this.vehicleMonthlyRate = vehicleMonthlyRate;
    }

    public double getMotorcycleMinuteRate() {
        return motorcycleMinuteRate;
    }
// Espacio para manejar la parte de vehiculo 
    public void setMotorcycleMinuteRate(double motorcycleMinuteRate) {
        this.motorcycleMinuteRate = motorcycleMinuteRate;
    }

    public double getMotorcycleHourlyRate() {
        return motorcycleHourlyRate;
    }

    public void setMotorcycleHourlyRate(double motorcycleHourlyRate) {
        this.motorcycleHourlyRate = motorcycleHourlyRate;
    }

    public double getMotorcycleDailyRate() {
        return motorcycleDailyRate;
    }

    public void setMotorcycleDailyRate(double motorcycleDailyRate) {
        this.motorcycleDailyRate = motorcycleDailyRate;
    }

    public double getMotorcycleWeeklyRate() {
        return motorcycleWeeklyRate;
    }

    public void setMotorcycleWeeklyRate(double motorcycleWeeklyRate) {
        this.motorcycleWeeklyRate = motorcycleWeeklyRate;
    }

    public double getMotorcycleMonthlyRate() {
        return motorcycleMonthlyRate;
    }

    public void setMotorcycleMonthlyRate(double motorcycleMonthlyRate) {
        this.motorcycleMonthlyRate = motorcycleMonthlyRate;
    }
}
