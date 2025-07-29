package foro.hub.api.domain.usuario;

import foro.hub.api.domain.ModificarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrar(DatosRegistroUsuario datos) {
        String contrasenaEncriptada = passwordEncoder.encode(datos.contrasena());
        var datosConEncriptacion = new DatosRegistroUsuario(datos.login(), contrasenaEncriptada);
        var usuario = new Usuario(datosConEncriptacion);
        return repository.save(usuario);
    }

    public Page<DatosListaUsuario> listar(Pageable paginacion) {
        return repository.findAllByActivoTrue(paginacion).map(DatosListaUsuario::new);
    }

    public Usuario actualizar(DatosActualizarUsuario datos) {
        var usuario = repository.getReferenceById(datos.id());
        validarAcceso(usuario);
        usuario.actualizarInformacion(datos);
        return usuario;
    }

    public void eliminar(Long id) {
        var usuario = repository.getReferenceById(id);
        validarAcceso(usuario);
        usuario.eliminar();
    }

    private void validarAcceso(Usuario usuario) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!usuario.getUsername().equals(login)) {
            throw new ModificarException("No tienes permiso para modificar este usuario");
        }
    }

}
