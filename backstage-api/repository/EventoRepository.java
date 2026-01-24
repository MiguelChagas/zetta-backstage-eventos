package br.ufla.zetta.backstage_api.repository;

import br.ufla.zetta.backstage_api.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByOrganizadorId(Long usuarioId);
}