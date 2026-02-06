package e_commerce.service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import e_commerce.exception.EstoqueInsuficienteException;
import e_commerce.models.dto.CompraRequest;
import e_commerce.models.entity.Produto;
import e_commerce.models.entity.Usuario;
import e_commerce.models.entity.Venda;
import e_commerce.repository.ProdutoRepository;
import e_commerce.repository.UsuarioRepository;
import e_commerce.repository.VendaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {

    private static final Logger logger = Logger.getLogger(CompraService.class.getName());

    private final ProdutoRepository produtoRepository;
    private final VendaRepository vendaRepository;
    private final UsuarioRepository usuarioRepository;
    
    @Transactional
    public Venda realizarCompra(CompraRequest compraRequest, String usuarioEmail) {
        Produto produto = produtoRepository.findById(compraRequest.getProdutoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + compraRequest.getProdutoId()));

        if (!produto.temEstoqueSuficiente(compraRequest.getQuantidade())) {
            throw new EstoqueInsuficienteException(
                "Estoque insuficiente para o produto: " + produto.getNome() +
                ". Disponível: " + produto.getEstoque() +
                ", Requerido: " + compraRequest.getQuantidade());
        }

        produto.baixarEstoque(compraRequest.getQuantidade());
        produtoRepository.save(produto);

        Venda venda = new Venda();

        Usuario usuario = null;
        if (usuarioEmail != null) {
            usuario = usuarioRepository.findByEmail(usuarioEmail).orElse(null);
        }
        venda.setUsuario(usuario);
        venda.setProduto(produto);
        venda.setQuantidade(compraRequest.getQuantidade());
        venda.setValorTotal(produto.getPreco() * compraRequest.getQuantidade());
        venda.setDataVenda(LocalDateTime.now());

        Venda vendaSalva = vendaRepository.save(venda);
        
        logger.info("Compra realizada com sucesso: " + vendaSalva);
        
        return vendaSalva;
    }
}
