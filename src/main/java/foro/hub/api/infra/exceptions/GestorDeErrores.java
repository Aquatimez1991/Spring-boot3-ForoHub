package foro.hub.api.infra.exceptions;

import foro.hub.api.domain.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity getionarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity getionarError400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errores.stream().map(DatosErrorValidacion::new).toList());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity gestionarError400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity gestionarErrorBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity gestionarErrorAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario inactivo o credenciales inválidas");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity gestionarErrorAccesoDenegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity gestionarError500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorDeValidacion(ValidacionException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    public record DatosErrorValidacion(String campo, String mensaje) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(ModificarException.class)
    public ResponseEntity gestionarErrorMoficacion(ModificarException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(ValidacionIntegridad.class)
    public ResponseEntity<String> manejarErrorIntegridad(ValidacionIntegridad ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<DatosError> manejarUsuarioNoEncontrado(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DatosError("Usuario no encontrado", "El usuario ingresado no existe."));
    }

    @ExceptionHandler(InactivoException.class)
    public ResponseEntity<DatosError> manejarUsuarioDeshabilitado(InactivoException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new DatosError("Usuario inactivo", "Tu cuenta está desactivada."));
    }
    public record DatosError(String error, String mensaje) {
    }

    @ExceptionHandler(InactivoTopicoException.class)
    public ResponseEntity<DatosError> manejarTopicoInactivo(InactivoTopicoException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new DatosError("Tópico inactivo", ex.getMessage()));
    }

    @ExceptionHandler(InactivoRespuestaException.class)
    public ResponseEntity<DatosError> manejarRespuestaInactivo(InactivoRespuestaException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new DatosError("Respuesta inactivo", ex.getMessage()));
    }
}