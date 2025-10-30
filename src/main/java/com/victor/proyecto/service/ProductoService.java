package com.victor.proyecto.service;


import com.victor.proyecto.dto.ProductoMapper;
import com.victor.proyecto.dto.ProductoRequestDTO;
import com.victor.proyecto.dto.ProductoResponseDTO;
import com.victor.proyecto.model.Producto;
import com.victor.proyecto.repository.CategoriaRepository;
import com.victor.proyecto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService implements IntProductoService {

    private final ProductoRepository repo;
    private final CategoriaRepository categoriaRepo; // <-- INYECTADA POR LOMBOK

    @Override
    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        Producto p = ProductoMapper.toEntity(dto);
        if (dto.getCategoriaId() != null) {
            var cat = categoriaRepo.findById(dto.getCategoriaId()).orElseThrow();
            p.setCategoria(cat);
        }
        return ProductoMapper.toResponse(repo.save(p));
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
        Producto ex = repo.findById(id).orElseThrow();
        ProductoMapper.updateEntity(ex, dto);

        if (dto.getCategoriaId() != null) {
            var cat = categoriaRepo.findById(dto.getCategoriaId()).orElseThrow();
            ex.setCategoria(cat);
        } else {
            ex.setCategoria(null); // opcional: permite quitar categoría
        }

        return ProductoMapper.toResponse(repo.save(ex));
    }

    @Override
    public ProductoResponseDTO porId(Long id) {
        return repo.findById(id).map(ProductoMapper::toResponse)
                .orElseThrow();
    }

    @Override
    public List<ProductoResponseDTO> listar() {
        return repo.findAll().stream().map(ProductoMapper::toResponse).toList();
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
