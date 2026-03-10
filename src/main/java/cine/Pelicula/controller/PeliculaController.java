package cine.Pelicula.controller;

import cine.Pelicula.dto.PeliculaDtoRequest;
import cine.Pelicula.dto.PeliculaDtoResponse;
import cine.Pelicula.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "02 Películas", description = "Gestión de películas del sistema")
@RestController
@RequestMapping("/peliculas")
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    @Operation(summary = "Crear película")
    @PostMapping
    public ResponseEntity<PeliculaDtoResponse> crear(@RequestBody PeliculaDtoRequest request) {

        PeliculaDtoResponse pelicula = peliculaService.crear(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(pelicula);
    }

    @Operation(summary = "Listar películas con paginación")
    @GetMapping
    public ResponseEntity<Page<PeliculaDtoResponse>> listar(@ParameterObject Pageable pageable) {

        return ResponseEntity.ok(peliculaService.listar(pageable));
    }

    @Operation(summary = "Buscar película por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDtoResponse> buscar(
            @Parameter(description = "ID de la película")
            @PathVariable Long id) {

        return ResponseEntity.ok(peliculaService.buscarPorId(id));
    }

    @Operation(summary = "Actualizar película")
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDtoResponse> actualizar(
            @Parameter(description = "ID de la película")
            @PathVariable Long id,
            @RequestBody PeliculaDtoRequest request) {

        return ResponseEntity.ok(peliculaService.actualizar(id, request));
    }

    @Operation(summary = "Eliminar película")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la película")
            @PathVariable Long id) {

        peliculaService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}