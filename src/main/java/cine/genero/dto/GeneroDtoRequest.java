package cine.genero.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Datos necesarios para crear un género de película")
public class GeneroDtoRequest {

    @Schema(
            description = "Nombre del género",
            example = "Ciencia Ficción",
            minLength = 3,
            maxLength = 50
    )
    @NotBlank
    @Size(min = 3, max = 50)
    private String nombre;
}