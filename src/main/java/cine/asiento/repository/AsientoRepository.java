package cine.asiento.repository;

import cine.asiento.AsientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsientoRepository extends JpaRepository<AsientoEntity,Long> {

    List<AsientoEntity> findBySalaId(Long salaId);
}
