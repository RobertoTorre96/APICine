package cine.Pelicula.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Información de una película")
public class PeliculaDtoResponse {

    @Schema(
            description = "Código identificador de la película",
            example = "PEL-001"
    )
    private String cod;

    @Schema(
            description = "Título de la película",
            example = "Interstellar"
    )
    private String titulo;

    @Schema(
            description = "Duración de la película en minutos",
            example = "169"
    )
    private Integer duracion;

    @Schema(
            description = "Sinopsis de la película",
            example = "Un grupo de exploradores viaja a través de un agujero de gusano en el espacio..."
    )
    private String sinopsis;

    @Schema(
            description = "Lista de géneros de la película",
            example = "[\"Ciencia Ficción\", \"Aventura\"]"
    )
    private List<String> generos;
}