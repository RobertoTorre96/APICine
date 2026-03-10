package cine.Pelicula.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Datos necesarios para crear o actualizar una película")
public class PeliculaDtoRequest {

    @Schema(
            description = "Código identificador de la película",
            example = "PEL-001"
    )
    @NotBlank
    private String cod;

    @Schema(
            description = "Título de la película",
            example = "Interstellar"
    )
    @NotBlank
    private String titulo;

    @Schema(
            description = "Duración de la película en minutos",
            example = "169",
            minimum = "15"
    )
    @Min(15)
    private int duracion;

    @Schema(
            description = "Sinopsis de la película",
            example = "Un grupo de exploradores viaja a través de un agujero de gusano en el espacio..."
    )
    @NotBlank
    private String sinopsis;

    @Schema(
            description = "Lista de IDs de géneros asociados a la película",
            example = "[1, 3, 5]"
    )
    @NotNull
    private List<Long> generoIds;
}