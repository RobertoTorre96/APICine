package cine.sala.repository;

import cine.sala.SalaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<SalaEntity, Long> {

    boolean existsByCod(String cod);

    Optional<SalaEntity> findByCod(String cod);
}