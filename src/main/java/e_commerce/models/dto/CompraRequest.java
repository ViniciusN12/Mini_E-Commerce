package e_commerce.models.dto;

import lombok.Data;

@Data
public class CompraRequest {
    private Long produtoId;
    private Integer quantidade;
}