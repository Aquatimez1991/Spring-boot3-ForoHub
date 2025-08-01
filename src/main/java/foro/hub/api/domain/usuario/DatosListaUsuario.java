package foro.hub.api.domain.usuario;

public record DatosListaUsuario(
        Long id,
        String login) {
    public DatosListaUsuario(Usuario usuario) {
        this(
        usuario.getId(),
        usuario.getLogin()
        );
    }
}
