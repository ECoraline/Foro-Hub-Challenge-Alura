package com.Aluracursos.ForoHub.Foro.Hub.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        String curso
) {
    public DatosListadoTopicos(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getFechaCreacion(), topico.getAutor(), topico.getCurso());
    }
}
