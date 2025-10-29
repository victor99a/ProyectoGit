package model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carrito_item")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CarritoItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // este nombre "carrito" DEBE coincidir con mappedBy en Carrito
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precioUnitario; // snapshot del precio del producto
}
