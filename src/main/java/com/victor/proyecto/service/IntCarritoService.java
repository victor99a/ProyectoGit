package com.victor.proyecto.service;


import com.victor.proyecto.dto.CarritoItemRequestDTO;
import com.victor.proyecto.dto.CarritoResponseDTO;

public interface IntCarritoService {
    CarritoResponseDTO verCarrito(Long usuarioId);
    CarritoResponseDTO agregarItem(Long usuarioId, CarritoItemRequestDTO req);
    CarritoResponseDTO actualizarCantidad(Long usuarioId, Long itemId, Integer nuevaCantidad);
    CarritoResponseDTO eliminarItem(Long usuarioId, Long itemId);
    void vaciar(Long usuarioId);
}
