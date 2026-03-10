package cine.sala.controller;

import cine.sala.dto.SalaDtoRequest;
import cine.sala.dto.SalaDtoResponse;
import cine.sala.service.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "04 Salas", description = "Gestión de salas de cine")
@RestController
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalaController {

    private final SalaService salaService;

    @Operation(summary = "Crear una nueva sala")
    @PostMapping
    public ResponseEntity<SalaDtoResponse> crear(@RequestBody SalaDtoRequest request) {

        SalaDtoResponse sala = salaService.crear(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(sala);
    }

    @Operation(summary = "Listar salas con paginación")
    @GetMapping
    public ResponseEntity<Page<SalaDtoResponse>> listar(@ParameterObject Pageable pageable) {

        return ResponseEntity.ok(salaService.listar(pageable));
    }

    @Operation(summary = "Eliminar sala por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la sala")
            @PathVariable Long id) {

        salaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}