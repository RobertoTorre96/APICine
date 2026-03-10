package cine.mapper;

import cine.Pelicula.PeliculaEntity;
import cine.Pelicula.PeliculaGeneroEntity;
import cine.Pelicula.dto.PeliculaDtoRequest;
import cine.Pelicula.dto.PeliculaDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")

public interface PeliculaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "funciones", ignore = true)
    @Mapping(target = "peliculaGeneros", ignore = true)
    PeliculaEntity requestToEntity(PeliculaDtoRequest request);

    @Mapping(target = "generos", source = "peliculaGeneros")
    PeliculaDtoResponse entityToResponse(PeliculaEntity entity);


    default List<String> nombreGeneros(Set<PeliculaGeneroEntity> relaciones) {
        return relaciones.stream()
                .map(pg -> pg.getGenero().getNombre())
                .toList();
    }

}
