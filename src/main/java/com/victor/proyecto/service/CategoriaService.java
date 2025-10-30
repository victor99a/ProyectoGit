package com.victor.proyecto.service;


import com.victor.proyecto.dto.CategoriaMapper;
import com.victor.proyecto.dto.CategoriaRequestDTO;
import com.victor.proyecto.dto.CategoriaResponseDTO;
import com.victor.proyecto.model.Categoria;
import com.victor.proyecto.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CategoriaService implements IntCategoriaService {
    private final CategoriaRepository repo;

    @Override public CategoriaResponseDTO crear(CategoriaRequestDTO dto){
        return CategoriaMapper.toResponse(repo.save(CategoriaMapper.toEntity(dto)));
    }
    @Override public CategoriaResponseDTO actualizar(Long id, CategoriaRequestDTO dto){
        Categoria ex = repo.findById(id).orElseThrow();
        CategoriaMapper.updateEntity(ex, dto);
        return CategoriaMapper.toResponse(repo.save(ex));
    }
    @Override public CategoriaResponseDTO porId(Long id){
        return repo.findById(id).map(CategoriaMapper::toResponse).orElseThrow();
    }
    @Override public List<CategoriaResponseDTO> listar(){
        return repo.findAll().stream().map(CategoriaMapper::toResponse).toList();
    }
    @Override public void eliminar(Long id){ repo.deleteById(id); }
}
