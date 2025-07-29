package foro.hub.api.domain.usuario;

public record DatosDetalleUsuario(Long id, String login) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin());
    }
}
