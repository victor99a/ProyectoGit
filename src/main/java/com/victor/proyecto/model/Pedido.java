package com.victor.proyecto.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="pedido")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false) private Long usuarioId;
    @Enumerated(EnumType.STRING) @Column(nullable=false)
    private PedidoEstado estado;
    @Column(nullable=false) private Double total;
    private LocalDateTime fecha;

    @OneToMany(mappedBy="pedido", cascade=CascadeType.ALL, orphanRemoval=true)
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();
}
