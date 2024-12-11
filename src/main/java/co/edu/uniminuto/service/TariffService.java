package co.edu.uniminuto.service;

import co.edu.uniminuto.model.Tariff;
import co.edu.uniminuto.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TariffService {

    @Autowired
    private TariffRepository tariffRepository;

    /**
     * Método para obtener las tarifas actuales desde la base de datos.
     * Si no hay tarifas configuradas, devuelve un objeto de tarifas por defecto.
     */
    public Tariff getCurrentTariff() {
        // Obtener las tarifas desde la base de datos
        Optional<Tariff> tariffOptional = tariffRepository.findById(1L); // Asumiendo que solo hay una tarifa general configurada
        return tariffOptional.orElseGet(this::getDefaultTariff); // Si no hay tarifa, devuelve una por defecto
    }

    /**
     * Método para devolver las tarifas por defecto si no se encuentran en la base de datos.
     */
    private Tariff getDefaultTariff() {
        Tariff defaultTariff = new Tariff();
        defaultTariff.setVehicleMinuteRate(0.1); // Tarifa por minuto para coches
        defaultTariff.setVehicleHourlyRate(1.0); // Tarifa por hora para coches
        defaultTariff.setVehicleDailyRate(10.0); // Tarifa por día para coches
        defaultTariff.setVehicleWeeklyRate(50.0); // Tarifa por semana para coches
        defaultTariff.setVehicleMonthlyRate(150.0); // Tarifa por mes para coches

        defaultTariff.setMotorcycleMinuteRate(0.05); // Tarifa por minuto para motos
        defaultTariff.setMotorcycleHourlyRate(0.5); // Tarifa por hora para motos
        defaultTariff.setMotorcycleDailyRate(5.0); // Tarifa por día para motos
        defaultTariff.setMotorcycleWeeklyRate(25.0); // Tarifa por semana para motos
        defaultTariff.setMotorcycleMonthlyRate(75.0); // Tarifa por mes para motos

        return defaultTariff; // Retorna tarifas por defecto
    }
}
