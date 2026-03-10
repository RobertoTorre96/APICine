package cine.funcion.service;

import cine.Pelicula.PeliculaEntity;
import cine.Pelicula.repository.PeliculaRepostory;
import cine.funcion.FuncionEntity;
import cine.funcion.dto.FuncionDroRequest;
import cine.funcion.dto.FuncionDtoResponse;
import cine.funcion.repository.FuncionRepository;
import cine.mapper.FuncionMapper;
import cine.sala.SalaEntity;
import cine.sala.repository.SalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FuncionService {
    private final FuncionRepository funcionRepository;
    private final PeliculaRepostory peliculaRepostory;
    private final SalaRepository salaRepository;
    private final  FuncionMapper funcionMapper;

    @Transactional
    public FuncionDtoResponse crear (FuncionDroRequest request){

        PeliculaEntity pelicula=peliculaRepostory.findById(request.getPeliculaId())
                .orElseThrow(()->new RuntimeException( "Pelicula:"+request.getPeliculaId()+" no encontrada"));

        SalaEntity sala=salaRepository.findById(request.getSalaId())
                .orElseThrow(()->new RuntimeException( "sala:"+request.getSalaId()+" no encontrada"));

        if (funcionRepository.existsBySalaIdAndFechaHora(request.getSalaId()
                                                    ,request.getFechaHora())){
            throw  new RuntimeException("ya existe una funcion en esa sala a esa hora");
        }
        FuncionEntity entity=FuncionEntity.builder()
                .pelicula(pelicula)
                .sala(sala)
                .fechaHora(request.getFechaHora())
                .precio(request.getPrecio())
                .build();

        FuncionEntity guardada =funcionRepository.save(entity);
        return funcionMapper.toResponse(guardada);
    }

    @Transactional(readOnly = true)
    public Page<FuncionDtoResponse>listar(Pageable pageable){
        return funcionRepository.findAll(pageable).map(funcionMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public  FuncionDtoResponse buscarPorId(Long id){
        FuncionEntity entity=funcionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("funcion:;"+id+" no encontrada"));

        return  funcionMapper.toResponse(entity);
    }

    @Transactional(readOnly = true)
    public Page<FuncionDtoResponse> listarPorPelicula(Long peliculaId, Pageable pageable) {

        return funcionRepository.findByPeliculaId(peliculaId, pageable)
                .map(funcionMapper::toResponse);
    }

    @Transactional(readOnly = true)
    public Page<FuncionDtoResponse> listarPorSala(Long salaId, Pageable pageable) {

        return funcionRepository.findBySalaId(salaId, pageable)
                .map(funcionMapper::toResponse);
    }

    @Transactional
    public void eliminar(Long id) {

        FuncionEntity funcion = funcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        if (!funcion.getReservas().isEmpty()) {
            throw new RuntimeException("No se puede eliminar una función con reservas");
        }

        funcionRepository.delete(funcion);
    }





}
