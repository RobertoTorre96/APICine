package cine.Pelicula.repository;

import cine.Pelicula.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeliculaRepostory extends JpaRepository<PeliculaEntity,Long> {
    Optional<PeliculaEntity> findByCod(String cod);
    Optional<PeliculaEntity> findById(Long id);

    boolean existsByCod(String cod);
}
