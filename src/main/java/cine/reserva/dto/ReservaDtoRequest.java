package cine.reserva.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.List;

@Value
@Schema(description = "Datos necesarios para crear una reserva")
public class ReservaDtoRequest {

    @Schema(
            description = "ID de la función a reservar",
            example = "5"
    )
    long funcionId;

    @Schema(
            description = "ID del usuario que realiza la reserva",
            example = "2"
    )
    Long usuarioId;

    @Schema(
            description = "Lista de IDs de los asientos a reservar",
            example = "[10, 11, 12]"
    )
    List<Long> asientosIds;
}