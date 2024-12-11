package co.edu.uniminuto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.model.Tariff;
import co.edu.uniminuto.repository.TariffRepository;

@RestController
@RequestMapping("/admin/tariffss") // Ruta para las tarifas
public class TariffController {

    @Autowired
    private TariffRepository tariffRepository;

    // Obtener las tarifas actuales (Motos y Carros)
    @GetMapping
    public ResponseEntity<Tariff> getCurrentTariffs() {
        Tariff tariffs = tariffRepository.findById(1L).orElse(null); // Buscamos tarifa con ID 1

        if (tariffs == null) {
            return ResponseEntity.notFound().build(); // Devuelve 404 si no se encuentra
        }
        return ResponseEntity.ok(tariffs); // Devuelve la tarifa encontrada
    }

    // Actualizar las tarifas (Motos y Carros)
    @PutMapping("/{id}")
    public ResponseEntity<Tariff> updateTariffs(@PathVariable Long id, @RequestBody Tariff tariff) {
        // Validación de tarifas
    	/*if (tariff.getVehicleMinuteRate() <= 0 ||
            tariff.getVehicleHourlyRate() <= 0 ||
            tariff.getVehicleDailyRate() <= 0 ||
            tariff.getVehicleWeeklyRate() <= 0 ||
            tariff.getVehicleMonthlyRate() <= 0 ||
            tariff.getMotorcycleMinuteRate() <= 0 ||
            tariff.getMotorcycleHourlyRate() <= 0 ||
            tariff.getMotorcycleDailyRate() <= 0 ||
            tariff.getMotorcycleWeeklyRate() <= 0 ||
            tariff.getMotorcycleMonthlyRate() <= 0) {
            return ResponseEntity.badRequest().body(null); 
        }*/

        // Verifica si la tarifa con el ID existe
        Tariff existingTariff = tariffRepository.findById(id).orElse(null);
        if (existingTariff == null) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no existe
        }

        // Actualiza las tarifas
        existingTariff.setVehicleMinuteRate(tariff.getVehicleMinuteRate());
        existingTariff.setVehicleHourlyRate(tariff.getVehicleHourlyRate());
        existingTariff.setVehicleDailyRate(tariff.getVehicleDailyRate());
        existingTariff.setVehicleWeeklyRate(tariff.getVehicleWeeklyRate());
        existingTariff.setVehicleMonthlyRate(tariff.getVehicleMonthlyRate());

        existingTariff.setMotorcycleMinuteRate(tariff.getMotorcycleMinuteRate());
        existingTariff.setMotorcycleHourlyRate(tariff.getMotorcycleHourlyRate());
        existingTariff.setMotorcycleDailyRate(tariff.getMotorcycleDailyRate());
        existingTariff.setMotorcycleWeeklyRate(tariff.getMotorcycleWeeklyRate());
        existingTariff.setMotorcycleMonthlyRate(tariff.getMotorcycleMonthlyRate());

        // Guarda la tarifa actualizada
        Tariff updatedTariff = tariffRepository.save(existingTariff);
        return ResponseEntity.ok(updatedTariff); // Retorna las tarifas actualizadas
    }
}