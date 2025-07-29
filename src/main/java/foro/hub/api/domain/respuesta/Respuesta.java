package foro.hub.api.domain.respuesta;

import foro.hub.api.domain.topico.Topico;
import foro.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topico")
    private Topico topico;

    private String mensaje;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    private Boolean activo = true;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id_respuesta_padre")
    //private Respuesta respuestaPadre;

    public Respuesta(DatosRegistroRespuesta datos, Usuario usuario, Topico topico) {
        this.usuario = usuario;
        this.topico = topico;
        this.mensaje = datos.mensaje();
        this.activo = true;
    }

    public void actualizar(String mensaje) {
        if (mensaje != null && !mensaje.trim().isEmpty()) {
            this.mensaje = mensaje;
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
