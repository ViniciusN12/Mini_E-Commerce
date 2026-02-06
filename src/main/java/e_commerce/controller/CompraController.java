package e_commerce.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import e_commerce.models.dto.CompraRequest;
import e_commerce.service.CompraService;
import e_commerce.service.ProdutoService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CompraController {
    
    private final CompraService compraService;
    private final ProdutoService produtoService;

    @GetMapping("/comprar/{produtoId}")
    public String comprarProduto(@PathVariable Long produtoId, Model model) {
        model.addAttribute("produto", produtoService.getProdutoById(produtoId));
        CompraRequest compraRequest = new CompraRequest();
        compraRequest.setProdutoId(produtoId);
        compraRequest.setQuantidade(1);
        model.addAttribute("compraRequest", compraRequest);
        return "compraProduto";
    }

    @PostMapping("/comprar")
    public String comprarPorodutoSubmit(CompraRequest compraRequest, Principal principal, RedirectAttributes redirectAttributes) {
        String usuarioEmail = principal != null ? principal.getName() : null;
        compraService.realizarCompra(compraRequest, usuarioEmail);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Compra realizada com sucesso!");
        return "redirect:/";
    }
}
