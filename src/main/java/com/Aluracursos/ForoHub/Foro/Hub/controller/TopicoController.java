package com.Aluracursos.ForoHub.Foro.Hub.controller;


import com.Aluracursos.ForoHub.Foro.Hub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    TopicoRepository topicosRepository;

    @PostMapping
    public ResponseEntity createTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                       UriComponentsBuilder uriComponentsBuilder){
        if(topicosRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())){
            return ResponseEntity.badRequest().body("Topico ya existe");
        }
        Topico topico = topicosRepository.save(new Topico(datosRegistroTopico));
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRegistroTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> getTopicos(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(topicosRepository.findByStatusTrue(paginacion).map(DatosListadoTopicos::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopico(@PathVariable Long id){
        if (id == null) {
            return ResponseEntity.badRequest().body("Id no puede ser nulo");
        }
        Topico topico = null;
        try {
            topico = topicosRepository.getReferenceById(id);
            var datosRespuestaTopico = new DatosRespuestaTopico(
                    topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),topico.getStatus(), topico.getAutor(), topico.getCurso());
            return ResponseEntity.ok(datosRespuestaTopico);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateTopico(@PathVariable Long id, @RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        if (id == null) {
            return ResponseEntity.badRequest().body("Id no puede ser nulo");
        }
        if(topicosRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())){
            return ResponseEntity.badRequest().body("la actualizacion coincide con un topico existente");
        }
        Optional<Topico> topico = topicosRepository.findById(id);
        if (topico.isPresent()) {
            topico.get().actualizarDatos(datosRegistroTopico);
            return ResponseEntity.ok(new DatosRegistroTopico(topico.get().getTitulo(), topico.get().getMensaje(), topico.get().getAutor(), topico.get().getCurso()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopico(@PathVariable Long id){
        if (id == null) {
            return ResponseEntity.badRequest().body("Id no puede ser nulo");
        }
        Optional<Topico> topico = topicosRepository.findById(id);
        if (topico.isPresent()) {
            topicosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
