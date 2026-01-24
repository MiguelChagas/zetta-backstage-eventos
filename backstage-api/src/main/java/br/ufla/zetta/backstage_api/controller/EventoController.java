package br.ufla.zetta.backstage_api.controller;

import br.ufla.zetta.backstage_api.model.Evento;
import br.ufla.zetta.backstage_api.service.EventoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@Tag(name = "Eventos", description = "Gerenciamento de eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@Valid @RequestBody Evento evento, @RequestParam Long usuarioId) {
        Evento novoEvento = eventoService.criarEvento(evento, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEvento);
    }

    @GetMapping("/produtor/{usuarioId}")
    public ResponseEntity<List<Evento>> listarEventos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(eventoService.listarEventosDoProdutor(usuarioId));
    }
}