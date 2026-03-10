package cine.reserva.service;

import cine.asiento.AsientoEntity;
import cine.asiento.repository.AsientoRepository;
import cine.funcion.FuncionEntity;
import cine.funcion.repository.FuncionRepository;
import cine.reserva.ReservaAsientoEntity;
import cine.reserva.ReservaEntity;
import cine.reserva.dto.ReservaDtoRequest;
import cine.reserva.dto.ReservaDtoResponse;
import cine.reserva.repository.ReservaAsientoRepository;
import cine.reserva.repository.ReservaRepository;
import cine.usuario.UsuarioEntity;
import cine.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaAsientoRepository reservaAsientoRepository;

    private final UsuarioRepository usuarioRepository;
    private final FuncionRepository funcionRepository;
    private final AsientoRepository asientoRepository;

    @Transactional
    public ReservaDtoResponse crear(ReservaDtoRequest request) {

        UsuarioEntity usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        FuncionEntity funcion = funcionRepository.findById(request.getFuncionId())
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        List<AsientoEntity> asientos = asientoRepository.findAllById(request.getAsientosIds());
        if (asientos.size() != request.getAsientosIds().size()) {
            throw new RuntimeException("Algún asiento no existe");
        }

        // validar asientos ocupados
        for (AsientoEntity asiento : asientos) {

            boolean ocupado = reservaAsientoRepository
                    .existsByFuncionIdAndAsientoId(funcion.getId(), asiento.getId());

            if (ocupado) {
                throw new RuntimeException("El asiento " + asiento.getCod() + " ya está reservado");
            }
        }

        ReservaEntity reserva = ReservaEntity.builder()
                .usuario(usuario)
                .funcion(funcion)
                .fecha(LocalDateTime.now())
                .codigo(UUID.randomUUID().toString().substring(0,8))
                .build();

        for (AsientoEntity asiento : asientos) {

            ReservaAsientoEntity reservaAsiento = ReservaAsientoEntity.builder()
                    .reserva(reserva)
                    .asiento(asiento)
                    .funcion(funcion)
                    .build();

            reserva.getAsientos().add(reservaAsiento);
        }

        ReservaEntity guardada = reservaRepository.save(reserva);

        List<String> codigos = guardada.getAsientos()
                .stream()
                .map(a -> a.getAsiento().getCod())
                .toList();

        return ReservaDtoResponse.builder()
                .id(guardada.getId())
                .usuarioId(usuario.getId())
                .funcionId(funcion.getId())
                .asientos(codigos)
                .fecha(guardada.getFecha())
                .codigo(guardada.getCodigo())
                .build();
    }
}

