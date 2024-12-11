package co.edu.uniminuto.security;

import co.edu.uniminuto.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        // Asignamos el rol al usuario, asegurándonos de que el rol tenga el prefijo "ROLE_"
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Puedes personalizar la lógica si lo necesitas
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Personaliza según la lógica de tu aplicación
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Personaliza según la lógica de tu aplicación
    }

    @Override
    public boolean isEnabled() {
        return true; // Personaliza según la lógica de tu aplicación
    }
}
