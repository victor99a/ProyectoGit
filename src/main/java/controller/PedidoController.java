package controller;

import lombok.RequiredArgsConstructor;
import model.Pedido;
import org.springframework.web.bind.annotation.*;
import service.IntPedidoService;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final IntPedidoService svc;

    @PostMapping("/checkout")
    public Pedido checkout(@RequestParam Long usuarioId){
        return svc.checkout(usuarioId);
    }
}
