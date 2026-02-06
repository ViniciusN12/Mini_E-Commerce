package e_commerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import e_commerce.models.entity.Usuario;


@Controller
public class AuthController {
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }
}
