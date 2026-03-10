package cine.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Schema(description = "Datos necesarios para crear un usuario")
public class UsuarioDtoRequest {

    @Schema(
            description = "Nombre del usuario",
            example = "JuanPerez",
            minLength = 3,
            maxLength = 15
    )
    @NotBlank
    @Size(min = 3, max = 15)
    private String nombre;

    @Schema(
            description = "Email del usuario",
            example = "juan@email.com"
    )
    @Email
    private String email;

    @Schema(
            description = "Contraseña del usuario",
            example = "1234",
            minLength = 4,
            maxLength = 8
    )
    @Size(min = 4, max = 8)
    private String password;

}