package dto;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PedidoDetalleDTO {
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}
