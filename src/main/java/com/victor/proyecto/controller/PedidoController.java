package com.victor.proyecto.controller;


import com.victor.proyecto.dto.PedidoMapper;
import com.victor.proyecto.dto.PedidoResponseDTO;
import com.victor.proyecto.service.IntPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
