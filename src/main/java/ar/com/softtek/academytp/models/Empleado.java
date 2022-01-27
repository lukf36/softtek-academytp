package ar.com.softtek.academytp.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DiscriminatorValue("empleado")
public class Empleado extends Usuario {

    @ManyToOne()
    @JoinColumn(name = "supervisor_id")
    private Empleado supervisor;
}
