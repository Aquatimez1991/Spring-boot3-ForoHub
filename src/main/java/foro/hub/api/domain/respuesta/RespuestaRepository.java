package foro.hub.api.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    Page<Respuesta> findAllByActivoTrue(Pageable pageable);
    boolean existsByUsuarioIdAndTopicoId(Long usuarioId, Long topicoId);

}
