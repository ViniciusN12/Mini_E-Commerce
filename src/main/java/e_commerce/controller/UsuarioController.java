package e_commerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import e_commerce.service.UsuarioService;
import e_commerce.models.entity.Usuario;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/register")
    public String registerUsuario(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String telefone,
            @RequestParam String senha,
            @RequestParam String confirm_password) {
        if (!senha.equals(confirm_password)) {
            return "redirect:/register?error=password_mismatch";
        }
        Usuario usuario = new Usuario();
        usuario.setNome(name);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setSenha(senha);

        usuarioService.registerUsuario(usuario);
        return "redirect:/login?success=registered";
    }
}
