package foro.hub.api.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistroTopico(
        @NotBlank String mensaje,
        @NotNull NombreCurso nombreCurso,
        @NotBlank String titulo) {
}
