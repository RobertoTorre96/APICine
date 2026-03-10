package cine.sala.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Información de una sala de cine")
public class SalaDtoResponse {

    @Schema(
            description = "ID único de la sala",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Código identificador de la sala",
            example = "SALA-1"
    )
    private String cod;

    @Schema(
            description = "Cantidad total de asientos disponibles",
            example = "200"
    )
    private int cantidadAsientos;
}