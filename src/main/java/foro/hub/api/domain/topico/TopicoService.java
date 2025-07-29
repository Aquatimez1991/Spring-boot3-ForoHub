package foro.hub.api.domain.topico;

import foro.hub.api.domain.AccesoModificarDenegadoException;
import foro.hub.api.domain.ValidacionException;
import foro.hub.api.domain.usuario.Usuario;
import foro.hub.api.domain.usuario.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Topico registrarTopico(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionException("Ya existe un tópico con ese título y mensaje.");
        }
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = (Usuario) usuarioRepository.findByLogin(login);

        Topico topico = new Topico(datos, usuario);
        return topicoRepository.save(topico);
    }

    public Topico actualizarTopico(DatosActualizacionTopico datos) {
        Topico topico = topicoRepository.getReferenceById(datos.id());
        validarPropietario(topico);
        topico.actualizarInformaciones(datos);
        return topico;
    }

    public void eliminarTopico(Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        validarPropietario(topico);
        topico.eliminar();
    }

    private void validarPropietario(Topico topico) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!topico.getUsuario().getUsername().equals(login)) {
            throw new AccesoModificarDenegadoException("No tienes permiso para modificar este tópico");
        }
    }
}
