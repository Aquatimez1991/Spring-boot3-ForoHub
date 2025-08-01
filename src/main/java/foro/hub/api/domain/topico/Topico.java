package foro.hub.api.domain.topico;

import foro.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    private String mensaje;
    @Enumerated(EnumType.STRING)
    private NombreCurso nombreCurso;

    private String titulo;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    public Topico(DatosRegistroTopico datos, Usuario usuario) {
        this.activo = true;
        this.usuario = usuario;
        this.mensaje = datos.mensaje();
        this.nombreCurso = datos.nombreCurso();
        this.titulo = datos.titulo();
    }

    public void actualizarInformaciones(@Valid DatosActualizacionTopico datos) {
        if (datos.mensaje() != null) this.mensaje = datos.mensaje();
        if (datos.nombreCurso() != null) this.nombreCurso = datos.nombreCurso();
        if (datos.titulo() != null) this.titulo = datos.titulo();
    }

    public void eliminar() {
        this.activo = false;
    }
}
