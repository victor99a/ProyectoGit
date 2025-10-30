package com.victor.proyecto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "categoria")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Categoria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String nombre;
}
