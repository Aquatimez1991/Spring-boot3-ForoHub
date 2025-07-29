package foro.hub.api.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum NombreCurso {
    @JsonAlias("java") JAVA,
    @JsonAlias("phyton") PHYTON,
    @JsonAlias("angular") ANGULAR,
    @JsonAlias("logica") LOGICA
}
