package cine.genero.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "Información de un género de película")
public class GeneroDtoResponse {

    @Schema(
            description = "ID del género",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Nombre del género",
            example = "Ciencia Ficción"
    )
    private String nombre;
}