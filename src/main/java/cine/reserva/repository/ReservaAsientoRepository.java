package cine.reserva.repository;

import cine.reserva.ReservaAsientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaAsientoRepository extends JpaRepository<ReservaAsientoEntity,Long> {

    boolean existsByFuncionIdAndAsientoId(Long funcionId,Long asientoId);
}
