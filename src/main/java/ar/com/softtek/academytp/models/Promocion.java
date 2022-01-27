package ar.com.softtek.academytp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "promocion")
public class Promocion {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @OneToOne(targetEntity = Producto.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;

    @Column(name = "descuento")
    private int descuento;

    @Column(name = "fecha_vigencia_desde")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fechaVigenciaDesde;

    @Column(name = "fecha_vigencia_hasta")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date fechaVigenciaHasta;
}
