package co.edu.uniminuto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.uniminuto.model.User;
import co.edu.uniminuto.repository.UserRepository;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public RedirectView registerUser(
            @ModelAttribute User user,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {
        
        // Validaciones
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "El usuario ya existe");
            return new RedirectView("/api/users/register");
        }

        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden");
            return new RedirectView("/api/users/register");
        }

        // Validar rol
        String role = user.getRole();
        if (role == null || (!role.equals("ADMIN") && !role.equals("USER"))) {
            model.addAttribute("error", "Rol no válido");
            return new RedirectView("/api/users/register");
        }

        // Hashear contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Guardar usuario
        userRepository.save(user);

        // Redirigir a login con mensaje de éxito
        RedirectView redirectView = new RedirectView("/login");
        redirectView.addStaticAttribute("message", "Usuario registrado exitosamente. Inicie sesión.");
        return redirectView;
    }
} 