package cine.funcion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Schema(description = "Información de una función de cine")
public class FuncionDtoResponse {

    @Schema(
            description = "ID de la función",
            example = "10"
    )
    private Long id;

    @Schema(
            description = "Título de la película",
            example = "Interstellar"
    )
    private String pelicula;

    @Schema(
            description = "Código o nombre de la sala",
            example = "SALA-1"
    )
    private String sala;

    @Schema(
            description = "Fecha y hora de la función",
            example = "2026-03-10T21:30:00"
    )
    private LocalDateTime fehcaHora;

    @Schema(
            description = "Precio de la entrada",
            example = "3500.00"
    )
    private BigDecimal precio;
}