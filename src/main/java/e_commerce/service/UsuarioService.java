package e_commerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import e_commerce.repository.UsuarioRepository;

import e_commerce.models.entity.Usuario;

import e_commerce.models.Role;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario registerUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        if (usuario.getRole() == null) {
            usuario.setRole(Role.USER);
        }
        return usuarioRepository.save(usuario);
    }
}
