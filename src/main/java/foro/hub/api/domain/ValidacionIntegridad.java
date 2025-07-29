package foro.hub.api.domain;

public class ValidacionIntegridad extends RuntimeException {
    public ValidacionIntegridad(String mensaje) {
        super(mensaje);
    }
}
