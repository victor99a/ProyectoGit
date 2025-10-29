package controller;

import dto.CarritoItemRequestDTO;
import dto.CarritoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IntCarritoService;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final IntCarritoService svc;

    // en este ejemplo, el usuario se envía por query para simplificar
    @GetMapping
    public CarritoResponseDTO ver(@RequestParam Long usuarioId){
        return svc.verCarrito(usuarioId);
    }

    @PostMapping("/items")
    public CarritoResponseDTO agregar(@RequestParam Long usuarioId,
                                       @RequestBody CarritoItemRequestDTO req){
        return svc.agregarItem(usuarioId, req);
    }

    @PutMapping("/items/{itemId}")
    public CarritoResponseDTO actualizar(@RequestParam Long usuarioId,
                                         @PathVariable Long itemId,
                                         @RequestParam Integer cantidad){
        return svc.actualizarCantidad(usuarioId, itemId, cantidad);
    }

    @DeleteMapping("/items/{itemId}")
    public CarritoResponseDTO eliminar(@RequestParam Long usuarioId,
                                       @PathVariable Long itemId){
        return svc.eliminarItem(usuarioId, itemId);
    }

    @DeleteMapping
    public ResponseEntity<Void> vaciar(@RequestParam Long usuarioId){
        svc.vaciar(usuarioId);
        return ResponseEntity.noContent().build();
    }
}
