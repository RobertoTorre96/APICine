package cine.mapper;

import cine.genero.GeneroEntity;
import cine.genero.dto.GeneroDtoRequest;
import cine.genero.dto.GeneroDtoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneroMapper {

    GeneroEntity requestToEntity(GeneroDtoRequest dto);

    GeneroDtoResponse entityToResponse(GeneroEntity entity);
}
