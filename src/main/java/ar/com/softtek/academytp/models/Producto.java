package ar.com.softtek.academytp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    @NotBlank(message = "Debes incluir el nombre")
    private String nombre;

    @Column(name = "precio")
    @NotNull(message = "Debes incluir el precio")
    @Min(value = 1, message = "El precio no puede ser menor a 1")
    private Integer precio;

    @Column(name = "stock")
    @NotNull(message = "Debes incluir el stock")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @ManyToMany(targetEntity = Proveedor.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "producto_proveedor",
            joinColumns = { @JoinColumn(name = "producto_id") },
            inverseJoinColumns = { @JoinColumn(name = "proveedor_id") }
    )
    private List<Proveedor> proveedores;

    private Boolean hasPromocion = false;
}
