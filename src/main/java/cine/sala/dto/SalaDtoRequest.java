package cine.sala.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "Datos necesarios para crear una sala de cine")
public class SalaDtoRequest {

    @Schema(
            description = "Código identificador de la sala",
            example = "SALA-1"
    )
    @NotBlank
    private String cod;

    @Schema(
            description = "Cantidad de filas de asientos",
            example = "10",
            minimum = "1",
            maximum = "26"
    )
    @Min(value = 1, message = "Debe haber al menos 1 fila")
    @Max(value = 26, message = "Máximo 26 filas (A-Z)")
    private int filas;

    @Schema(
            description = "Cantidad de columnas de asientos",
            example = "20",
            minimum = "1",
            maximum = "50"
    )
    @Min(value = 1, message = "Debe haber al menos 1 columna")
    @Max(value = 50, message = "Máximo 50 columnas")
    private int columnas;
}