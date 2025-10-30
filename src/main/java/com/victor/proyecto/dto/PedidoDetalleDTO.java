package com.victor.proyecto.dto;

import lombok.Data;

@Data
public class PedidoDetalleDTO {
    private Long id;            // ← NECESARIO
    private Long productoId;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}
