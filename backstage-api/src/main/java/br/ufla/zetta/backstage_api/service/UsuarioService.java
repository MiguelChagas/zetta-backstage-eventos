package br.ufla.zetta.backstage_api.service;

import br.ufla.zetta.backstage_api.model.Usuario;
import br.ufla.zetta.backstage_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario salvarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("Já existe um produtor cadastrado com este email.");
        }
        return usuarioRepository.save(usuario);
    }
    
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produtor não encontrado."));
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email ou senha inválidos"));

        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Email ou senha inválidos");
        }

        return usuario;
    }
}