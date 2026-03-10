package cine.genero.controller;

import cine.genero.dto.GeneroDtoRequest;
import cine.genero.dto.GeneroDtoResponse;
import cine.genero.service.GeneroService;
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

@Tag(name = "03 Géneros", description = "Gestión de géneros de películas")
@RestController
@RequestMapping("/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService generoService;

    @Operation(summary = "Crear género")
    @PostMapping
    public ResponseEntity<GeneroDtoResponse> crear(@RequestBody GeneroDtoRequest request) {

        GeneroDtoResponse genero = generoService.crear(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(genero);
    }

    @Operation(summary = "Listar géneros con paginación")
    @GetMapping
    public ResponseEntity<Page<GeneroDtoResponse>> listar(@ParameterObject Pageable pageable) {

        return ResponseEntity.ok(generoService.listar(pageable));
    }

    @Operation(summary = "Buscar género por ID")
    @GetMapping("/{id}")
    public ResponseEntity<GeneroDtoResponse> buscar(
            @Parameter(description = "ID del género")
            @PathVariable Long id) {

        return ResponseEntity.ok(generoService.buscarPorId(id));
    }

    @Operation(summary = "Eliminar género")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del género")
            @PathVariable Long id) {

        generoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}