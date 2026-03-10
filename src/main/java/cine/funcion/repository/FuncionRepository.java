package cine.funcion.repository;

import cine.funcion.FuncionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FuncionRepository extends JpaRepository<FuncionEntity,Long> {
    boolean existsBySalaIdAndFechaHora (long salaId, LocalDateTime fechaHora);
    Page<FuncionEntity> findByPeliculaId(Long peliculaId,Pageable pageable);
    Page<FuncionEntity> findBySalaId(Long salaId,Pageable pageable);
}
