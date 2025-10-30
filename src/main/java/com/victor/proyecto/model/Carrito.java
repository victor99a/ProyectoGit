package com.victor.proyecto.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrito")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Carrito {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // referencia simple al usuario (puedes migrar luego a @ManyToOne Usuario)
    @Column(nullable = false)
    private Long usuarioId;

    // mappedBy = "carrito" (el nombre del atributo en CarritoItem)
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<CarritoItem> items = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private Double total = 0.0; // recálculalo cuando modifiques items
}
