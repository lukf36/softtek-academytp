package ar.com.softtek.academytp.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Debes introducir tu nombre de usuario")
    private String username;

    @NotBlank(message = "Debes introducir tu contracenia")
    private String password;
}
