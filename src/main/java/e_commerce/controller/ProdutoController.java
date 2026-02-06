package e_commerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import e_commerce.models.entity.Produto;
import e_commerce.service.ProdutoService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    // Public index for normal users
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("produtos", produtoService.getAllProdutos());
        return "index";
    }

    // Admin dashboard
    @GetMapping("/produtosAdmin")
    public String adminIndex(Model model) {
        model.addAttribute("produtos", produtoService.getAllProdutos());
        return "produtosAdmin";
    }

    // Keep old /produtos route redirecting to public index
    @GetMapping("/produtos")
    public String produtosPublic(Model model) {
        return "redirect:/";
    }

    @GetMapping("/produtos/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "createProduto";
    }

    @PostMapping("/produtos/salvar")
    public String salvarProduto(Produto produto) {
        produtoService.saveProduto(produto);
        return "redirect:/produtosAdmin";
    }

    @GetMapping("/produtos/excluir/{id}")
    public String excluirProduto(@PathVariable Long id) {
        Produto produto = produtoService.getProdutoById(id);
        if (produto != null) {
            produtoService.deleteProduto(id);
        }
        return "redirect:/produtosAdmin";
    }

    @GetMapping("/produtos/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoService.getProdutoById(id);
        if (produto != null) {
            model.addAttribute("produto", produto);
            return "editProduto";
        }
        return "redirect:/produtosAdmin";
    }

    @PostMapping("/produtos/atualizar/{id}")
    public String atualizarProduto(@PathVariable Long id, Produto produto) {
        produto.setId(id);
        produtoService.saveProduto(produto);
        return "redirect:/produtosAdmin";
    }
}
