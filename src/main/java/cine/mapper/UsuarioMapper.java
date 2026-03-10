package cine.mapper;

import cine.usuario.UsuarioEntity;
import cine.usuario.dto.UsuarioDtoRequest;
import cine.usuario.dto.UsuarioDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "role", ignore = true)
    UsuarioEntity requestToEntity(UsuarioDtoRequest u);
    UsuarioDtoResponse entityToResponse(UsuarioEntity u);



}
