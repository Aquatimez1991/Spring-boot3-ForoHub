package foro.hub.api.domain;

public class InactivoRespuestaException extends RuntimeException {
    public InactivoRespuestaException(String mensaje) {
        super(mensaje);
    }
}
