package ar.com.softtek.academytp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @ManyToMany(targetEntity = Categoria.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "proveedor_categoria",
            joinColumns = { @JoinColumn(name = "proveedor_id") },
            inverseJoinColumns = { @JoinColumn(name = "categoria_id") }
    )
    private List<Categoria> categorias;
}
