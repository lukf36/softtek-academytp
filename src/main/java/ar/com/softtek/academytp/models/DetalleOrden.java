package ar.com.softtek.academytp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "detalle_orden")
public class DetalleOrden {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "precio")
    private int precio;

    @Column(name = "cantidad")
    private int cantidad;

    @OneToOne(targetEntity = Producto.class)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;
}
