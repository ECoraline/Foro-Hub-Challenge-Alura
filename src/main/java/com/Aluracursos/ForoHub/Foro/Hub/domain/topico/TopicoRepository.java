package com.Aluracursos.ForoHub.Foro.Hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByStatusTrue(Pageable pageable);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

}
