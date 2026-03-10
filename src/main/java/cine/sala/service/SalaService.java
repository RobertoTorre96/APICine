package cine.sala.service;

import cine.asiento.AsientoEntity;
import cine.sala.SalaEntity;
import cine.sala.dto.SalaDtoRequest;
import cine.sala.dto.SalaDtoResponse;
import cine.sala.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SalaService {

    private final SalaRepository salaRepository;

    @Transactional
    public SalaDtoResponse crear(SalaDtoRequest request) {

        if (salaRepository.existsByCod(request.getCod())) {
            throw new RuntimeException("La sala ya existe");
        }

        SalaEntity sala = SalaEntity.builder()
                .cod(request.getCod())
                .build();

        //  Generar asientos automáticamente
        char ultimaFila = (char) ('A' + request.getFilas() - 1);
        for (char fila = 'A';fila<=ultimaFila; fila++) {
            for (int columna = 1; columna <= request.getColumnas(); columna++) {

                String codigoAsiento = fila+columna+"";

                AsientoEntity asiento = AsientoEntity.builder()
                        .cod(codigoAsiento)
                        .sala(sala)
                        .build();

                sala.getAsientos().add(asiento);
            }
        }
        SalaEntity guardada = salaRepository.save(sala);
        return SalaDtoResponse.builder()
                .id(guardada.getId())
                .cod(guardada.getCod())
                .cantidadAsientos(guardada.getAsientos().size())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<SalaDtoResponse> listar(Pageable pageable) {

        return salaRepository.findAll(pageable)
                .map(sala -> SalaDtoResponse.builder()
                        .id(sala.getId())
                        .cod(sala.getCod())
                        .cantidadAsientos(sala.getAsientos().size())
                        .build());
    }

    @Transactional
    public void eliminar(Long id) {

        SalaEntity sala = salaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        if (!sala.getFunciones().isEmpty()) {
            throw new RuntimeException("No se puede eliminar una sala con funciones asignadas");
        }

        salaRepository.delete(sala);
    }


}
