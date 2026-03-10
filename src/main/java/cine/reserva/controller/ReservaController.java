package cine.reserva.controller;

import cine.reserva.dto.ReservaDtoRequest;
import cine.reserva.dto.ReservaDtoResponse;
import cine.reserva.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "06 Reservas", description = "Gestión de reservas de funciones de cine")
@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @Operation(
            summary = "Crear reserva",
            description = "Permite reservar uno o varios asientos para una función"
    )
    @PostMapping
    public ResponseEntity<ReservaDtoResponse> crear(@RequestBody ReservaDtoRequest request) {

        ReservaDtoResponse reserva = reservaService.crear(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(reserva);
    }
}