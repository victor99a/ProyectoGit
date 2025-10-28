package service;

import dto.ProductoRequestDTO;
import dto.ProductoResponseDTO;

import java.util.List;

public interface IntProductoService {
    ProductoResponseDTO crear(ProductoRequestDTO dto);
    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto);
    ProductoResponseDTO porId(Long id);
    List<ProductoResponseDTO> listar();
    void eliminar(Long id);
}
