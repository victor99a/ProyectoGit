package dto;

import lombok.experimental.UtilityClass;
import model.DetallePedido;
import model.Pedido;

import java.util.List;

@UtilityClass
public class PedidoMapper {

    public PedidoDetalleDTO toDetalleDTO(DetallePedido d){
        return PedidoDetalleDTO.builder()
                .productoId(d.getProducto().getId())
                .productoNombre(d.getProducto().getNombre())
                .cantidad(d.getCantidad())
                .precioUnitario(d.getPrecioUnitario())
                .subtotal(d.getSubtotal())
                .build();
    }

    public PedidoResponseDTO toResponse(Pedido p){
        List<PedidoDetalleDTO> dets = p.getDetalles().stream()
                .map(PedidoMapper::toDetalleDTO).toList();

        return PedidoResponseDTO.builder()
                .id(p.getId())
                .usuarioId(p.getUsuarioId())
                .estado(p.getEstado().name())
                .total(p.getTotal())
                .fecha(p.getFecha())
                .detalles(dets)
                .build();
    }
}
