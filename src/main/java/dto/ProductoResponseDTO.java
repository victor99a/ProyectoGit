package dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private String descripcion;
    private Long categoriaId; // simple para mostrar a qué categoría pertenece
    private String categoriaNombre; // útil para la vista

}

