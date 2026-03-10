package cine.genero.service;

import cine.genero.GeneroEntity;
import cine.genero.dto.GeneroDtoRequest;
import cine.genero.dto.GeneroDtoResponse;
import cine.genero.repository.GeneroRepository;
import cine.mapper.GeneroMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;
    private final GeneroMapper generoMapper;

    @Transactional
    public GeneroDtoResponse crear(GeneroDtoRequest request) {

        if (generoRepository.existsByNombre(request.getNombre())){
            throw new EntityExistsException("el genero "+ request.getNombre()+" ya esxite");
        }

        GeneroEntity entity= generoMapper.requestToEntity(request);
        GeneroEntity guardado= generoRepository.save(entity);

        return  generoMapper.entityToResponse(guardado);
    }

    @Transactional(readOnly = true)
    public Page<GeneroDtoResponse> listar(Pageable pageable) {

        return generoRepository.findAll(pageable)
                .map(generoMapper::entityToResponse);
    }

    @Transactional(readOnly = true)
    public GeneroDtoResponse buscarPorId(Long id){

        GeneroEntity entity=generoRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("no se ah encontrado el genero "+id));

        return generoMapper.entityToResponse(entity);
    }

    @Transactional
    public void eliminar(Long id) {

        GeneroEntity entity=generoRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("no se ah encontrado el genero:"+id));
        generoRepository.delete(entity);
    }

    }
