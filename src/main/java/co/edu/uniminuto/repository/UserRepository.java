package co.edu.uniminuto.repository;

import co.edu.uniminuto.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// Interfaz para operaciones CRUD con la entidad User
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Busca un usuario por su username
    Optional<User> findByUsername(String username);
}
