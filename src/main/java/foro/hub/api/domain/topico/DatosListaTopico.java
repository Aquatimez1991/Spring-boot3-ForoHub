package foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String usuario,
        Long idUsuario,
        String mensaje,
        NombreCurso nombreCurso,
        String titulo,
        LocalDateTime fechaCreacion) {

    public DatosListaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getUsuario().getLogin(),
                topico.getUsuario().getId(),
                topico.getMensaje(),
                topico.getNombreCurso(),
                topico.getTitulo(),
                topico.getFechaCreacion()
        );
    }
}
