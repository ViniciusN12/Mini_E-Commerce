package e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import e_commerce.models.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    
}
