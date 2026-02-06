package e_commerce.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import e_commerce.models.dto.CompraRequest;
import e_commerce.models.entity.Venda;
import e_commerce.service.CompraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CompraRestController {
    
    private final CompraService compraService;

    @PostMapping("/comprar")
    public ResponseEntity<Venda> comprar(@RequestBody CompraRequest compraRequest, Principal principal) {
        String usuarioEmail = principal != null ? principal.getName() : null;
        Venda venda = compraService.realizarCompra(compraRequest, usuarioEmail);
        return ResponseEntity.ok(venda);
    }
}
