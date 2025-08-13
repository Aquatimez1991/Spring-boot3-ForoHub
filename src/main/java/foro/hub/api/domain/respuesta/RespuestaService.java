package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.InactivoRespuestaException;
import foro.hub.api.domain.InactivoTopicoException;
import foro.hub.api.domain.ValidacionIntegridad;
import foro.hub.api.domain.topico.DatosDetalleTopico;
import foro.hub.api.domain.topico.Topico;
import foro.hub.api.domain.topico.TopicoRepository;
import foro.hub.api.domain.usuario.Usuario;
import foro.hub.api.domain.usuario.UsuarioRepository;
import foro.hub.api.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {

    private final RespuestaRepository respuestaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TopicoRepository topicoRepository;

    public RespuestaService(RespuestaRepository respuestaRepository, UsuarioRepository usuarioRepository, TopicoRepository topicoRepository) {
        this.respuestaRepository = respuestaRepository;
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
    }

    public Respuesta registrarRespuesta(DatosRegistroRespuesta datos) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioRepository.findByLoginAndActivoTrue(login)
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado o inactivo"));
        Topico topico = topicoRepository.getReferenceById(datos.idTopico());

        boolean yaRespondio = respuestaRepository.existsByUsuarioIdAndTopicoId(usuario.getId(), topico.getId());

        if (yaRespondio) {
            throw new ValidacionIntegridad("Ya has respondido a este tópico.");
        }

        Respuesta respuesta = new Respuesta(datos, usuario, topico);
        return respuestaRepository.save(respuesta);
    }

    public DatosDetalleRespuesta obtenerDetalleRespuesta(Long id) {
        Respuesta respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("La respuesta no existe"));
        if (!respuesta.getActivo()) {
            throw new InactivoRespuestaException("La respuesta no está disponible porque está inactivo.");
        }
        return new DatosDetalleRespuesta(respuesta);
    }

    public Respuesta actualizarRespuesta(DatosActualizarRespuesta datos) {
        Respuesta respuesta = respuestaRepository.getReferenceById(datos.id());
        validarPropietario(respuesta);

        respuesta.actualizar(datos.mensaje());
        return respuesta;
    }


    public void eliminarRespuesta(Long id) {
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        validarPropietario(respuesta);
        respuesta.eliminar();
    }

    private void validarPropietario(Respuesta respuesta) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!respuesta.getUsuario().getUsername().equals(login)) {
            throw new ValidacionException("No tienes permiso para modificar esta respuesta");

        }
    }
}
