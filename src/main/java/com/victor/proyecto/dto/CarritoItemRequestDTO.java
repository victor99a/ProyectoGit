package com.victor.proyecto.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CarritoItemRequestDTO {
    private Long productoId;
    private Integer cantidad;
}
