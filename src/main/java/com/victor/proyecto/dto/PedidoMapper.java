package com.victor.proyecto.dto;

import com.victor.proyecto.model.DetallePedido;
import com.victor.proyecto.model.Pedido;
import java.util.stream.Collectors;

public final class PedidoMapper {

    private PedidoMapper() {}

    public static PedidoDetalleDTO toDetalleDTO(DetallePedido d) {
        PedidoDetalleDTO dto = new PedidoDetalleDTO();
        dto.setId(d.getId());

        // Si DetallePedido tiene: private Producto producto;
        // y NO tiene productoId, saca el id desde producto (con null-check por seguridad)
        Long prodId = (d.getProducto() != null) ? d.getProducto().getId() : null;
        dto.setProductoId(prodId);

        dto.setCantidad(d.getCantidad());
        dto.setPrecioUnitario(d.getPrecioUnitario());
        dto.setSubtotal(d.getPrecioUnitario() * d.getCantidad());
        return dto;
    }

    public static PedidoResponseDTO toResponse(Pedido p) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(p.getId());
        dto.setUsuarioId(p.getUsuarioId());
        dto.setTotal(p.getTotal());
        dto.setEstado(p.getEstado().toString()); // o p.getEstado() si es String
        dto.setDetalles(
                p.getDetalles().stream()
                        .map(PedidoMapper::toDetalleDTO)
                        .collect(Collectors.toList())
        );
        return dto;
    }
}
