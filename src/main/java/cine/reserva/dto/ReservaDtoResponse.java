package cine.reserva.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
@Schema(description = "Información de la reserva realizada")
public class ReservaDtoResponse {

    @Schema(
            description = "ID de la reserva",
            example = "15"
    )
    Long id;

    @Schema(
            description = "ID del usuario que realizó la reserva",
            example = "2"
    )
    Long usuarioId;

    @Schema(
            description = "ID de la función reservada",
            example = "5"
    )
    Long funcionId;

    @Schema(
            description = "Asientos reservados",
            example = "[\"A1\", \"A2\", \"A3\"]"
    )
    List<String> asientos;

    @Schema(
            description = "Fecha y hora en que se realizó la reserva",
            example = "2026-03-08T20:30:00"
    )
    LocalDateTime fecha;

    @Schema(
            description = "Código único de la reserva",
            example = "RSV-9F3K2L"
    )
    String codigo;
}