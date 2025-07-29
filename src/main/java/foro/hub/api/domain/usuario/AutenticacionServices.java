package foro.hub.api.domain.usuario;

import foro.hub.api.domain.InactivoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionServices implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username)
                .map(usuario -> {
                    if (!usuario.isEnabled()) {
                        throw new InactivoException("Usuario inactivo");
                    }
                    return usuario;
                })
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
