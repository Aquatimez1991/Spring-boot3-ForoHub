package foro.hub.api.domain.topico;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAllByActivoTrue(Pageable paginacion);
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    List<Topico> findByUsuarioIdAndActivoTrue(Long idUsuario);


}
