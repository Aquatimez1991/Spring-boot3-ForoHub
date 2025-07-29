package foro.hub.api.domain;

public class InactivoException extends RuntimeException {
    public InactivoException(String mensaje) {
        super(mensaje);
    }
}
