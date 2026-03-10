package cine.Pelicula.service;

import cine.Pelicula.PeliculaEntity;
import cine.Pelicula.PeliculaGeneroEntity;
import cine.Pelicula.dto.PeliculaDtoRequest;
import cine.Pelicula.dto.PeliculaDtoResponse;
import cine.Pelicula.repository.PeliculaRepostory;
import cine.genero.GeneroEntity;
import cine.genero.repository.GeneroRepository;
import cine.mapper.PeliculaMapper;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeliculaService {
    private final PeliculaRepostory peliculaRepository;
    private final GeneroRepository generoRepository;
    private final PeliculaMapper peliculaMapper;


    @Transactional
    public PeliculaDtoResponse crear(PeliculaDtoRequest request) {
        if (peliculaRepository.existsByCod(request.getCod())) {
            throw new EntityExistsException("La película ya existe");
        }

        PeliculaEntity entity = peliculaMapper.requestToEntity(request);
        List<GeneroEntity> generos = generoRepository.findAllById(request.getGeneroIds());

        if (generos.size() != request.getGeneroIds().size()) {
            throw new IllegalArgumentException("algun genero no existe.");
        }

        for (GeneroEntity genero : generos) {
            PeliculaGeneroEntity pg = PeliculaGeneroEntity.builder()
                    .pelicula(entity)
                    .genero(genero)
                    .build();
            entity.getPeliculaGeneros().add(pg);
        }

        PeliculaEntity guardada = peliculaRepository.save(entity);
        return peliculaMapper.entityToResponse(guardada);
    }

    @Transactional(readOnly = true)
    public Page<PeliculaDtoResponse> listar(Pageable pageable) {
        return peliculaRepository.findAll(pageable)
                .map(peliculaMapper::entityToResponse);
    }

    @Transactional(readOnly = true)
    public PeliculaDtoResponse buscarPorId(Long id) {
        PeliculaEntity pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrada"));

        return peliculaMapper.entityToResponse(pelicula);
    }
    @Transactional
    public PeliculaDtoResponse actualizar(Long id, PeliculaDtoRequest request) {

        PeliculaEntity pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));


        pelicula.setCod(request.getCod());
        pelicula.setTitulo(request.getTitulo());
        pelicula.setDuracion(request.getDuracion());
        pelicula.setSinopsis(request.getSinopsis());

        List<GeneroEntity> generosNuevos = generoRepository.findAllById(request.getGeneroIds());

        if (generosNuevos.size() != request.getGeneroIds().size()) {
            throw new RuntimeException("Algún género no existe");
        }


        Set<Long> nuevosIds = generosNuevos.stream()
                .map(GeneroEntity::getId)
                .collect(Collectors.toSet());


        pelicula.getPeliculaGeneros().removeIf(pg ->
                !nuevosIds.contains(pg.getGenero().getId())
        );

        // 5️⃣ Obtener IDs actuales luego de eliminar
        Set<Long> actualesIds = pelicula.getPeliculaGeneros().stream()
                .map(rel -> rel.getGenero().getId())
                .collect(Collectors.toSet());

        // 6️⃣ Agregar las nuevas que faltan
        for (GeneroEntity genero : generosNuevos) {
            if (!actualesIds.contains(genero.getId())) {
                PeliculaGeneroEntity nuevaRelacion = PeliculaGeneroEntity.builder()
                        .pelicula(pelicula)
                        .genero(genero)
                        .build();

                pelicula.getPeliculaGeneros().add(nuevaRelacion);
            }
        }

        return peliculaMapper.entityToResponse(pelicula);
    }

    @Transactional
    public void eliminar(Long id) {
        PeliculaEntity pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Película no encontrada"));

        if (!pelicula.getFunciones().isEmpty()) {
            throw new RuntimeException("No se puede eliminar una película con funciones");
        }
        peliculaRepository.deleteById(id);
    }


}
