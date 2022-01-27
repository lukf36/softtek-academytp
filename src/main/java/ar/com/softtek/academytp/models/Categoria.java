package ar.com.softtek.academytp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    @NotNull(message = "Debes incluir el nombre")
    @NotEmpty(message = "Debes incluir el nombre")
    private String nombre;
}
