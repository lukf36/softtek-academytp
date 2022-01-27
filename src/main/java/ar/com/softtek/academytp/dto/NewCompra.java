package ar.com.softtek.academytp.dto;

import ar.com.softtek.academytp.models.DetalleOrden;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewCompra {
    private Integer clienteId;
    private List<DetalleNewCompra> detalles;
}