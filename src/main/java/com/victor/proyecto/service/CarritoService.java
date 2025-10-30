package com.victor.proyecto.service;


import com.victor.proyecto.dto.CarritoItemRequestDTO;
import com.victor.proyecto.dto.CarritoItemResponseDTO;
import com.victor.proyecto.dto.CarritoResponseDTO;
import com.victor.proyecto.model.Carrito;
import com.victor.proyecto.model.CarritoItem;
import com.victor.proyecto.model.Producto;
import com.victor.proyecto.repository.CarritoItemRepository;
import com.victor.proyecto.repository.CarritoRepository;
import com.victor.proyecto.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CarritoService implements IntCarritoService {

    private final CarritoRepository carritoRepo;
    private final CarritoItemRepository itemRepo;
    private final ProductoRepository productoRepo;

    private Carrito getOrCreate(Long usuarioId){
        return carritoRepo.findByUsuarioId(usuarioId)
                .orElseGet(() -> carritoRepo.save(Carrito.builder()
                        .usuarioId(usuarioId).total(0.0).build()));
    }

    private CarritoResponseDTO toResponse(Carrito c){
        List<CarritoItemResponseDTO> items = c.getItems().stream().map(i ->
                CarritoItemResponseDTO.builder()
                        .id(i.getId())
                        .productoId(i.getProducto().getId())
                        .productoNombre(i.getProducto().getNombre())
                        .precioUnitario(i.getPrecioUnitario())
                        .cantidad(i.getCantidad())
                        .subtotal(i.getPrecioUnitario() * i.getCantidad())
                        .build()
        ).toList();
        double total = items.stream().mapToDouble(CarritoItemResponseDTO::getSubtotal).sum();
        c.setTotal(total);
        return CarritoResponseDTO.builder()
                .carritoId(c.getId()).usuarioId(c.getUsuarioId())
                .total(total).items(items).build();
    }

    @Override public CarritoResponseDTO verCarrito(Long usuarioId){
        return toResponse(getOrCreate(usuarioId));
    }

    @Override @Transactional
    public CarritoResponseDTO agregarItem(Long usuarioId, CarritoItemRequestDTO req){
        Carrito c = getOrCreate(usuarioId);
        Producto p = productoRepo.findById(req.getProductoId()).orElseThrow();

        // si ya existe el item, solo suma cantidad
        CarritoItem existente = c.getItems().stream()
                .filter(i -> i.getProducto().getId().equals(p.getId()))
                .findFirst().orElse(null);

        if (existente == null) {
            CarritoItem nuevo = CarritoItem.builder()
                    .carrito(c).producto(p).cantidad(req.getCantidad())
                    .precioUnitario(p.getPrecio())
                    .build();
            c.getItems().add(nuevo);
        } else {
            existente.setCantidad(existente.getCantidad() + req.getCantidad());
        }
        carritoRepo.save(c);
        return toResponse(c);
    }

    @Override @Transactional
    public CarritoResponseDTO actualizarCantidad(Long usuarioId, Long itemId, Integer nuevaCantidad){
        Carrito c = getOrCreate(usuarioId);
        CarritoItem it = c.getItems().stream().filter(i -> i.getId().equals(itemId))
                .findFirst().orElseThrow();
        if (nuevaCantidad <= 0) {
            c.getItems().remove(it);
            itemRepo.delete(it);
        } else {
            it.setCantidad(nuevaCantidad);
        }
        carritoRepo.save(c);
        return toResponse(c);
    }

    @Override @Transactional
    public CarritoResponseDTO eliminarItem(Long usuarioId, Long itemId){
        Carrito c = getOrCreate(usuarioId);
        CarritoItem it = c.getItems().stream().filter(i -> i.getId().equals(itemId))
                .findFirst().orElseThrow();
        c.getItems().remove(it);
        itemRepo.delete(it);
        carritoRepo.save(c);
        return toResponse(c);
    }

    @Override @Transactional
    public void vaciar(Long usuarioId){
        Carrito c = getOrCreate(usuarioId);
        c.getItems().clear();
        carritoRepo.save(c);
    }
}
