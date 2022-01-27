package ar.com.softtek.academytp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orden")
public class Orden {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @OneToOne(targetEntity = Empleado.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "empelado_id", referencedColumnName = "id")
    private Empleado vendedor;

    @Column(name = "fecha_generacion")
    private Date fechaGeneracion;

    @Column(name = "fecha_entrega")
    private Date fechaEntrega;

    @OneToMany(targetEntity = DetalleOrden.class, fetch = FetchType.LAZY)
    @JoinTable(name = "orden_detalle")
    private List<DetalleOrden> detalleOrden;

    public Orden() {
        detalleOrden = new ArrayList<>();
    }

    public void addDetalleOrden(DetalleOrden detalleOrden){
        this.detalleOrden.add(detalleOrden);
    }
}
