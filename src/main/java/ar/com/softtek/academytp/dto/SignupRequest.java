package ar.com.softtek.academytp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    @NotBlank(message = "El nombre de usuario no puede ser vacio")
    @Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
    private String username;

    @NotBlank(message = "La contraceña no puede ser vacia")
    @Size(min = 6, max = 40, message = "La contraceña debe tener entre 6 y 40 caracteres")
    private String password;

    @NotBlank(message = "El nombre no puede ser vacio")
    private String nombre;

    @NotBlank(message = "El apellido no puede ser vacio")
    private String apellido;

    @NotBlank(message = "La razon social no puede ser vacia")
    private String razonSocial;

    @NotBlank(message = "El cuit no puede ser vacio")
    private String cuit;

    @NotBlank(message = "El dni no puede ser vacio")
    private String dni;

    @NotBlank(message = "Debes especificar un tipo de cliente")
    private String tipoCliente;
}
