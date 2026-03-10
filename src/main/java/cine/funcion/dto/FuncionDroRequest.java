package cine.funcion.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Schema(description = "Datos necesarios para crear una función de cine")
public class FuncionDroRequest {

    @Schema(
            description = "ID de la película que se proyectará",
            example = "3"
    )
    @NotNull
    private Long peliculaId;

    @Schema(
            description = "ID de la sala donde se proyectará la película",
            example = "2"
    )
    @NotNull
    private Long salaId;

    @Schema(
            description = "Fecha y hora de la función",
            example = "2026-03-10T21:30:00"
    )
    @NotNull
    private LocalDateTime fechaHora;

    @Schema(
            description = "Precio de la entrada",
            example = "3500.00",
            minimum = "0.0"
    )
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal precio;
}