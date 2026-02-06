package e_commerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import e_commerce.models.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
