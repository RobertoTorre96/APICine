package cine.funcion.Controller;

import cine.funcion.dto.FuncionDroRequest;
import cine.funcion.dto.FuncionDtoResponse;
import cine.funcion.service.FuncionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "05 Funciones", description = "Gestión de funciones de cine")
@RestController
@RequestMapping("/funciones")
@RequiredArgsConstructor
public class FuncionController {

    private final FuncionService funcionService;

    @Operation(summary = "Crear función")
    @PostMapping
    public ResponseEntity<FuncionDtoResponse> crear(@RequestBody FuncionDroRequest request) {

        FuncionDtoResponse funcion = funcionService.crear(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcion);
    }

    @Operation(summary = "Listar funciones con paginación")
    @GetMapping
    public ResponseEntity<Page<FuncionDtoResponse>> listar(@ParameterObject Pageable pageable) {

        return ResponseEntity.ok(funcionService.listar(pageable));
    }

    @Operation(summary = "Buscar función por ID")
    @GetMapping("/{id}")
    public ResponseEntity<FuncionDtoResponse> buscar(
            @Parameter(description = "ID de la función")
            @PathVariable Long id) {

        return ResponseEntity.ok(funcionService.buscarPorId(id));
    }

    @Operation(summary = "Listar funciones por película")
    @GetMapping("/pelicula/{peliculaId}")
    public ResponseEntity<Page<FuncionDtoResponse>> listarPorPelicula(
            @Parameter(description = "ID de la película")
            @PathVariable Long peliculaId,
            @ParameterObject Pageable pageable) {

        return ResponseEntity.ok(funcionService.listarPorPelicula(peliculaId, pageable));
    }

    @Operation(summary = "Listar funciones por sala")
    @GetMapping("/sala/{salaId}")
    public ResponseEntity<Page<FuncionDtoResponse>> listarPorSala(
            @Parameter(description = "ID de la sala")
            @PathVariable Long salaId,
            @ParameterObject Pageable pageable) {

        return ResponseEntity.ok(funcionService.listarPorSala(salaId, pageable));
    }

    @Operation(summary = "Eliminar función")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID de la función")
            @PathVariable Long id) {

        funcionService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}