package foro.hub.api.domain.respuesta;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        String autor,
        Long idTopico,
        LocalDateTime fechaCreacion
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getUsuario().getLogin(),
                respuesta.getTopico().getId(),
                respuesta.getFechaCreacion()
        );
    }
}
