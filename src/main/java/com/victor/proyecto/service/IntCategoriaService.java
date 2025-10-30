package com.victor.proyecto.service;

import com.victor.proyecto.dto.CategoriaRequestDTO;
import com.victor.proyecto.dto.CategoriaResponseDTO;

import java.util.List;

public interface IntCategoriaService {
    CategoriaResponseDTO crear(CategoriaRequestDTO dto);
    CategoriaResponseDTO actualizar(Long id, CategoriaRequestDTO dto);
    CategoriaResponseDTO porId(Long id);
    List<CategoriaResponseDTO> listar();
    void eliminar(Long id);
}
