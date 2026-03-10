package cine.mapper;

import cine.funcion.FuncionEntity;
import cine.funcion.dto.FuncionDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FuncionMapper {

    @Mapping(source = "pelicula.titulo",target = "pelicula")
    @Mapping(source = "sala.cod",target = "sala")
    FuncionDtoResponse toResponse(FuncionEntity entity);
}
