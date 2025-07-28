package foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosListaTopico(
    Long id,
    String idUsuario,
    String mensaje,
    NombreCurso nombreCurso,
    String titulo,
    LocalDateTime fechaCreacion) {
    public DatosListaTopico(Topico topico) {
            this(
                    topico.getId(),
                    topico.getIdUsuario(),
                    topico.getMensaje(),
                    topico.getNombreCurso(),
                    topico.getTitulo(),
                    topico.getFechaCreacion()
            );
    }
}
