package com.victor.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Data                        // getters, setters, toString, equals/hashCode
@NoArgsConstructor           // ctor vacío (JPA lo requiere)
@AllArgsConstructor          // ctor con todos los campos
@Builder                     // builder opcional
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 500)
    private String descripcion;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}

