package foro.hub.api.domain;

public class InactivoTopicoException extends RuntimeException {
    public InactivoTopicoException(String mensaje) {
        super(mensaje);
    }
}
