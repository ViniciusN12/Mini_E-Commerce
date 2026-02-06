package e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import e_commerce.models.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
