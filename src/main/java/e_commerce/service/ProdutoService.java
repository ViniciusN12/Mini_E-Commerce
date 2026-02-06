package e_commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import e_commerce.models.entity.Produto;
import e_commerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoById(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }

    public Produto saveProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id);
    }
}
