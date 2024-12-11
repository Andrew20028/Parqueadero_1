package co.edu.uniminuto.repository;

import co.edu.uniminuto.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepository extends JpaRepository<Tariff, Long> {
    // Métodos para acceder a las tarifas en la base de datos
}
