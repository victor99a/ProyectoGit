package dto;

import lombok.experimental.UtilityClass;
import model.Producto;

@UtilityClass
public class ProductoMapper {

    public Producto toEntity(ProductoRequestDTO d) {
        if (d == null) return null;
        return Producto.builder()
                .nombre(d.getNombre())
                .precio(d.getPrecio())
                .stock(d.getStock())
                .descripcion(d.getDescripcion())
                .build();
    }

    public void updateEntity(Producto entity, ProductoRequestDTO d) {
        if (entity == null || d == null) return;
        entity.setNombre(d.getNombre());
        entity.setPrecio(d.getPrecio());
        entity.setStock(d.getStock());
        entity.setDescripcion(d.getDescripcion());
    }

    public ProductoResponseDTO toResponse(Producto p){
        return ProductoResponseDTO.builder()
                .id(p.getId()).nombre(p.getNombre()).precio(p.getPrecio())
                .stock(p.getStock()).descripcion(p.getDescripcion())
                .categoriaId(p.getCategoria() != null ? p.getCategoria().getId() : null)
                .categoriaNombre(p.getCategoria() != null ? p.getCategoria().getNombre() : null)
                .build();
    }

}
