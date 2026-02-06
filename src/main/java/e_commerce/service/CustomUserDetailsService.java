package e_commerce.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import e_commerce.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import e_commerce.models.entity.Usuario;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return User.builder()
            .username(usuario.getEmail())
            .password(usuario.getSenha())
            .roles(usuario.getRole() != null ? usuario.getRole().name() : "USER")
            .build();
    }
}