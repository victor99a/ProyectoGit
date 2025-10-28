package service;

import dto.ProductoMapper;
import dto.ProductoRequestDTO;
import dto.ProductoResponseDTO;
import lombok.RequiredArgsConstructor;
import model.Producto;
import org.springframework.stereotype.Service;
import repository.ProductoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService implements IntProductoService {

    private final ProductoRepository repo;

    @Override
    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        Producto p = ProductoMapper.toEntity(dto);
        return ProductoMapper.toResponse(repo.save(p));
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
        Producto ex = repo.findById(id).orElseThrow();
        ProductoMapper.updateEntity(ex, dto);
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
    public void eliminar(Long id) { repo.deleteById(id); }
}
