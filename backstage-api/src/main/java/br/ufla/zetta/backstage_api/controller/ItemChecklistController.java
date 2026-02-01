package br.ufla.zetta.backstage_api.controller;

import br.ufla.zetta.backstage_api.model.ItemChecklist;
import br.ufla.zetta.backstage_api.model.StatusItem;
import br.ufla.zetta.backstage_api.service.ItemChecklistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/itens")
public class ItemChecklistController {

    private final ItemChecklistService itemService;

    public ItemChecklistController(ItemChecklistService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemChecklist> adicionarItem(@RequestBody ItemChecklist item, @RequestParam Long eventoId) {
        ItemChecklist novoItem = itemService.adicionarItem(item, eventoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoItem);
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<ItemChecklist>> listarItens(@PathVariable Long eventoId) {
        return ResponseEntity.ok(itemService.listarItensDoEvento(eventoId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ItemChecklist> atualizarStatus(@PathVariable Long id, @RequestParam StatusItem status) {
        ItemChecklist itemAtualizado = itemService.atualizarStatus(id, status);
        return ResponseEntity.ok(itemAtualizado);
    }
}