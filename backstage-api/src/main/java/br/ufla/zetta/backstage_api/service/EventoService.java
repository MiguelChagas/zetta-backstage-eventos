package br.ufla.zetta.backstage_api.service;

import br.ufla.zetta.backstage_api.exception.RecursoNaoEncontradoException;
import br.ufla.zetta.backstage_api.model.Evento;
import br.ufla.zetta.backstage_api.model.Usuario;
import br.ufla.zetta.backstage_api.repository.EventoRepository;
import br.ufla.zetta.backstage_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioRepository usuarioRepository;

    public EventoService(EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Evento criarEvento(Evento evento, Long usuarioId) {
        Usuario produtor = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Produtor não encontrado com ID: " + usuarioId));
        
        evento.setOrganizador(produtor);
        return eventoRepository.save(evento);
    }

    public List<Evento> listarEventosDoProdutor(Long usuarioId) {
        return eventoRepository.findByOrganizadorId(usuarioId);
    }
}