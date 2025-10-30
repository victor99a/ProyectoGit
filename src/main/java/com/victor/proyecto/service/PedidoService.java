package com.victor.proyecto.service;

import com.victor.proyecto.model.*;
import com.victor.proyecto.repository.CarritoRepository;
import com.victor.proyecto.repository.PedidoRepository;
import com.victor.proyecto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

@Service @RequiredArgsConstructor
public class PedidoService implements IntPedidoService {
    private final CarritoRepository carritoRepo;
    private final PedidoRepository pedidoRepo;
    private final ProductoRepository productoRepo;

    @Override @Transactional
    public Pedido checkout(Long usuarioId) {
        Carrito carrito = carritoRepo.findByUsuarioId(usuarioId).orElseThrow();

        // validar stock
        carrito.getItems().forEach(it -> {
            Producto p = productoRepo.findById(it.getProducto().getId()).orElseThrow();
            if (p.getStock() < it.getCantidad())
                throw new RuntimeException("Stock insuficiente para " + p.getNombre());
        });

        // descontar stock
        carrito.getItems().forEach(it -> {
            Producto p = it.getProducto();
            p.setStock(p.getStock() - it.getCantidad());
            productoRepo.save(p);
        });

        // crear pedido + detalles
        Pedido pedido = Pedido.builder()
                .usuarioId(usuarioId).estado(PedidoEstado.CREADO)
                .fecha(LocalDateTime.now()).total(0.0).build();

        double total = 0.0;
        for (CarritoItem ci : carrito.getItems()) {
            DetallePedido d = DetallePedido.builder()
                    .pedido(pedido).producto(ci.getProducto())
                    .cantidad(ci.getCantidad())
                    .precioUnitario(ci.getPrecioUnitario())
                    .subtotal(ci.getPrecioUnitario() * ci.getCantidad())
                    .build();
            pedido.getDetalles().add(d);
            total += d.getSubtotal();
        }
        pedido.setTotal(total);
        pedidoRepo.save(pedido);

        // vaciar carrito
        carrito.getItems().clear();
        carritoRepo.save(carrito);

        return pedido;
    }
}
