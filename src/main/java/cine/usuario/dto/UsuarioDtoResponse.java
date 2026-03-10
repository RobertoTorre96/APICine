package cine.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Schema(description = "Datos de usuario devueltos por la API")
public class UsuarioDtoResponse {

    @Schema(
            description = "Nombre del usuario",
            example = "JuanPerez"
    )
    private String nombre;

    @Schema(
            description = "Email del usuario",
            example = "juan@email.com"
    )
    private String email;

}