package com.victor.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="detalle_pedido")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class DetallePedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="pedido_id")
    private Pedido pedido;

    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="producto_id")
    private Producto producto;

    @Column(nullable=false) private Integer cantidad;
    @Column(nullable=false) private Double precioUnitario;
    @Column(nullable=false) private Double subtotal;
}
