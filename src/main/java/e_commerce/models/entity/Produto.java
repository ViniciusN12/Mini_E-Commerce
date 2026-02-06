package e_commerce.models.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private Double preco;
    private String descricao;
    private Integer estoque; // RF1 - Campo obrigatório

    // RN1 - Método para validar estoque
    public boolean temEstoqueSuficiente(int quantidade) {
        return this.estoque != null && this.estoque >= quantidade;
    }

    // Método para baixar estoque após compra
    public void baixarEstoque(int quantidade) {
        this.estoque -= quantidade;
    }
}