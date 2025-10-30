package com.victor.proyecto.dto;

import lombok.*;
import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CarritoResponseDTO {
    private Long carritoId;
    private Long usuarioId;
    private Double total;
    private List<CarritoItemResponseDTO> items;
}
