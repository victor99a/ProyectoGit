package com.victor.proyecto.dto;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String nombre;
    private String apellido;
    private String correo;
    private int telefono;
    private String contrasena;
}
