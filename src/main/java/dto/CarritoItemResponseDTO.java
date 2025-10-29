package dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CarritoItemResponseDTO {
    private Long id;
    private Long productoId;
    private String productoNombre;
    private Double precioUnitario;
    private Integer cantidad;
    private Double subtotal;
}

