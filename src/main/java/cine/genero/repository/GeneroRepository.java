package cine.genero.repository;

import cine.genero.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<GeneroEntity,Long> {
    Optional<GeneroEntity> findById(Long id);

    boolean existsByNombre(String nombre);
}
