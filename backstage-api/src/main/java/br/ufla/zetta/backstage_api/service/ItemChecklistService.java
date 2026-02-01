package br.ufla.zetta.backstage_api.service;

import br.ufla.zetta.backstage_api.exception.RecursoNaoEncontradoException;
import br.ufla.zetta.backstage_api.model.Evento;
import br.ufla.zetta.backstage_api.model.ItemChecklist;
import br.ufla.zetta.backstage_api.model.StatusItem;
import br.ufla.zetta.backstage_api.repository.EventoRepository;
import br.ufla.zetta.backstage_api.repository.ItemChecklistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemChecklistService {

    private final ItemChecklistRepository itemRepository;
    private final EventoRepository eventoRepository;

    public ItemChecklistService(ItemChecklistRepository itemRepository, EventoRepository eventoRepository) {
        this.itemRepository = itemRepository;
        this.eventoRepository = eventoRepository;
    }
    
    @Transactional
    public ItemChecklist adicionarItem(ItemChecklist item, Long eventoId) {
        Evento evento = eventoRepository.findById(eventoId)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado."));
        
        item.setEvento(evento);
        item.setStatus(StatusItem.PENDENTE); 
        return itemRepository.save(item);
    }

    public List<ItemChecklist> listarItensDoEvento(Long eventoId) {
        if (!eventoRepository.existsById(eventoId)) {
            throw new RecursoNaoEncontradoException("Evento não encontrado.");
        }
        return itemRepository.findByEventoId(eventoId);
    }

    @Transactional
    public ItemChecklist atualizarStatus(Long itemId, StatusItem novoStatus) {
        ItemChecklist item = itemRepository.findById(itemId)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Item de checklist não encontrado."));
            
        item.setStatus(novoStatus);
        return itemRepository.save(item);
    }
}