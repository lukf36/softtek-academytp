package ar.com.softtek.academytp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@DiscriminatorValue("cliente")
public class Cliente extends Usuario {
    @Column(name = "razon_social")
    private String razonSocial;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "dni")
    private String dni;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente")
    private TipoCliente tipoCliente;

    @ManyToMany(targetEntity = Orden.class, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "cliente_orden",
            joinColumns = { @JoinColumn(name = "cliente_id") },
            inverseJoinColumns = { @JoinColumn(name = "orden_id") }
    )
    private List<Orden> ordenes;

    public Cliente() {
        this.ordenes = new ArrayList<>();
    }

    public void addOrden(Orden orden){
        this.ordenes.add(orden);
    }
}
