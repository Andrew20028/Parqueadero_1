package co.edu.uniminuto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.uniminuto.model.User;
import co.edu.uniminuto.repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Mostrar el formulario de login
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";  // Redirige a la vista de login
    }

    // Procesar el login
    @PostMapping("/login")
    public String processLogin(@ModelAttribute User user, Model model) {
        // Verifica las credenciales
        User existingUser = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (existingUser != null) {
            // Verifica si las contraseñas coinciden
            if (existingUser.getPassword().equals(user.getPassword())) {
                // Redirige según el rol
                if ("ADMIN".equals(existingUser.getRole())) {
                    model.addAttribute("message", "¡Bienvenido, Administrador!");
                    return "redirect:/admin";  // Redirige a la vista /admin
                } else if ("USER".equals(existingUser.getRole())) {
                    model.addAttribute("message", "¡Bienvenido, " + user.getUsername() + "!");
                    return "redirect:/user-parking-spots";  // Redirige a la vista /user-parking-spots
                }
            } else {
                model.addAttribute("error", "Contraseña incorrecta.");
            }
        } else {
            model.addAttribute("error", "Nombre de usuario no encontrado.");
        }

        // Si las credenciales son incorrectas
        return "login"; // Regresa al formulario de login si las credenciales son incorrectas
    }

    // Página de admin
    @GetMapping("/admin")  // Aquí ya está la ruta correcta para redirigir a /admin
    public String admin() {
        return "admin";  // Redirige a la vista admin.html
    }

    // Página de parking-spots para usuarios
    @GetMapping("/user-parking-spots")  // Ruta para los espacios de parqueo de usuario
    public String parkingSpots() {
        return "parking-spots";  // Redirige a la vista parking-spots.html
    }
}