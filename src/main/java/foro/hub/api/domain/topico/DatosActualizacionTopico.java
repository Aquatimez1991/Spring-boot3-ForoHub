package foro.hub.api.domain.topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizacionTopico(
        @NotNull Long id,
        String usuario,
        String mensaje,
        NombreCurso nombreCurso,
        String titulo,
        LocalDateTime fechaCreacion
) {
}
