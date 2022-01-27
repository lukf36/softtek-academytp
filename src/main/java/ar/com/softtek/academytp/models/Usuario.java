package ar.com.softtek.academytp.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Usuario {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name="nombre_usuario")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "is_admin")
    @ColumnDefault("false")
    private Boolean isAdmin;

    @Column(name = "is_empleado")
    @ColumnDefault("false")
    private Boolean isEmpleado;
}
