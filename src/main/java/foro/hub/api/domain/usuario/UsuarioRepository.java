package foro.hub.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Page<Usuario> findAllByActivoTrue(Pageable pageable);
    Optional<Usuario> findByLoginAndActivoTrue(String login);
    Optional<Usuario> findByLogin(String login);

}
