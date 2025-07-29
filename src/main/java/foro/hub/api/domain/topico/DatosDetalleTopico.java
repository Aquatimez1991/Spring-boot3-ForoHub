package foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String usuario,
        String mensaje,
        NombreCurso nombreCurso,
        String titulo,
        LocalDateTime fechaCreacion) {

    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getUsuario().getLogin(),
                topico.getMensaje(),
                topico.getNombreCurso(),
                topico.getTitulo(),
                topico.getFechaCreacion()
        );
    }
}
