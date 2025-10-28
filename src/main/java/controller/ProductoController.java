package controller;

import dto.ProductoRequestDTO;
import dto.ProductoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IntProductoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final IntProductoService svc;

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear( @RequestBody ProductoRequestDTO dto){
        ProductoResponseDTO res = svc.crear(dto);
        return ResponseEntity.created(URI.create("/api/productos/" + res.getId())).body(res);
    }

    @GetMapping
    public List<ProductoResponseDTO> listar(){
        return svc.listar();
    }

    @GetMapping("/{id}")
    public ProductoResponseDTO porId(@PathVariable Long id){
        return svc.porId(id);
    }

    @PutMapping("/{id}")
    public ProductoResponseDTO actualizar(@PathVariable Long id, @RequestBody ProductoRequestDTO dto){
        return svc.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        svc.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
