package br.ufla.zetta.backstage_api.repository;

import br.ufla.zetta.backstage_api.model.ItemChecklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemChecklistRepository extends JpaRepository<ItemChecklist, Long> {

    List<ItemChecklist> findByEventoId(Long eventoId);

    List<ItemChecklist> findByEventoIdAndStatus(Long eventoId, br.ufla.zetta.backstage_api.model.StatusItem status);

    List<ItemChecklist> findByEventoIdAndPrioridade(Long eventoId, br.ufla.zetta.backstage_api.model.Prioridade prioridade);
}