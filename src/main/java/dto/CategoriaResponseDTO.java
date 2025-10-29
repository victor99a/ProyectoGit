package dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoriaResponseDTO {
    private Long id;
    private String nombre;
}
