package com.victor.proyecto.dto;

import com.victor.proyecto.model.Categoria;

public final class CategoriaMapper {

    private CategoriaMapper() {} // evitar instanciación

    public static Categoria toEntity(CategoriaRequestDTO d) {
        Categoria c = new Categoria();
        c.setNombre(d.getNombre());
        return c;
    }

    public static void updateEntity(Categoria c, CategoriaRequestDTO d) {
        c.setNombre(d.getNombre());
    }

    public static CategoriaResponseDTO toResponse(Categoria c) {
        CategoriaResponseDTO r = new CategoriaResponseDTO();
        r.setId(c.getId());
        r.setNombre(c.getNombre());
        return r;
    }
}
