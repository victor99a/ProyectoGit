package controller;


import dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IntCategoriaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final IntCategoriaService svc;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> crear(@RequestBody CategoriaRequestDTO dto){
        var res = svc.crear(dto);
        return ResponseEntity.created(URI.create("/api/categorias/" + res.getId())).body(res);
    }
    @GetMapping public List<CategoriaResponseDTO> listar(){ return svc.listar(); }
    @GetMapping("/{id}") public CategoriaResponseDTO porId(@PathVariable Long id){ return svc.porId(id); }
    @PutMapping("/{id}") public CategoriaResponseDTO actualizar(@PathVariable Long id,  @RequestBody CategoriaRequestDTO dto){ return svc.actualizar(id, dto); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id){ svc.eliminar(id); return ResponseEntity.noContent().build(); }
}
