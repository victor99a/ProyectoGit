package dto;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class PedidoResponseDTO {
    private Long id;
    private Long usuarioId;
    private String estado;
    private Double total;
    private LocalDateTime fecha;
    private List<PedidoDetalleDTO> detalles;
}
