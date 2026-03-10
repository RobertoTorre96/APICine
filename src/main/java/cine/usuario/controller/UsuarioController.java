package cine.usuario.controller;

import cine.usuario.dto.UsuarioDtoRequest;
import cine.usuario.dto.UsuarioDtoResponse;
import cine.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "01 Usuarios", description = "Gestión de usuarios del sistema")
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Crear usuario")
    @PostMapping
    public ResponseEntity<UsuarioDtoResponse> crear(@RequestBody UsuarioDtoRequest request) {

        UsuarioDtoResponse usuario = usuarioService.crearUsuario(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @Operation(summary = "Crear usuario administrador")
    @PostMapping("/admin")
    public ResponseEntity<UsuarioDtoResponse> crearAdmin(@Valid @RequestBody UsuarioDtoRequest request) {

        UsuarioDtoResponse usuario = usuarioService.crearAdmin(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @Operation(summary = "Buscar usuario por email")
    @GetMapping("/{email}")
    public ResponseEntity<UsuarioDtoResponse> buscar(
            @Parameter(description = "Email del usuario")
            @PathVariable String email) {

        return ResponseEntity.ok(usuarioService.buscarUsuarioPorMail(email));
    }

    @Operation(summary = "Listar usuarios con paginación")
    @GetMapping
    public ResponseEntity<Page<UsuarioDtoResponse>> listar(Pageable pageable) {

        return ResponseEntity.ok(usuarioService.listarUsuarios(pageable));
    }

    @Operation(summary = "Eliminar usuario por email")
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "Email del usuario")
            @PathVariable String email) {

        usuarioService.eliminarPorEmail(email);

        return ResponseEntity.noContent().build();
    }
}