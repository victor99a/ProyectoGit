package controller;

import dto.PedidoMapper;
import dto.PedidoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import service.IntPedidoService;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final IntPedidoService svc;

    @PostMapping("/checkout")
    public PedidoResponseDTO checkout(@RequestParam Long usuarioId){
        return PedidoMapper.toResponse(svc.checkout(usuarioId));
    }
}
