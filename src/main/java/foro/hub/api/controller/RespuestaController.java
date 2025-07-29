package foro.hub.api.controller;

import foro.hub.api.domain.respuesta.*;
import foro.hub.api.domain.topico.DatosDetalleTopico;
import foro.hub.api.domain.topico.DatosListaTopico;
import foro.hub.api.domain.usuario.DatosActualizarUsuario;
import foro.hub.api.domain.usuario.DatosDetalleUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroRespuesta datos) {
        var respuesta = respuestaService.registrarRespuesta(datos);
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizarRespuesta datos) {
        var respuesta = respuestaService.actualizarRespuesta(datos);
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }


    @GetMapping
    public ResponseEntity<Page<DatosListaRespuesta>>listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacion) {
        var page = respuestaRepository.findAllByActivoTrue(paginacion).map(DatosListaRespuesta::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var respuesta = respuestaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return ResponseEntity.noContent().build();
    }
}
