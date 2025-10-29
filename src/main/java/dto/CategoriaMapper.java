package dto;

import lombok.experimental.UtilityClass;
import model.Categoria;

@UtilityClass
public class CategoriaMapper {
    public Categoria toEntity(CategoriaRequestDTO d){
        return Categoria.builder().nombre(d.getNombre()).build();
    }
    public void updateEntity(Categoria c, CategoriaRequestDTO d){
        c.setNombre(d.getNombre());
    }
    public CategoriaResponseDTO toResponse(Categoria c){
        return CategoriaResponseDTO.builder()
                .id(c.getId()).nombre(c.getNombre())
                .build();
    }
}
