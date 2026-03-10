package cine.asiento;

import cine.reserva.ReservaAsientoEntity;
import cine.sala.SalaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "asiento",
        uniqueConstraints =@UniqueConstraint(columnNames = {"sala_id","cod"})
)
public class AsientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String cod;

    @OneToMany (mappedBy = "asiento")
    private List<ReservaAsientoEntity> reservaAsiento= new ArrayList<>();

    @ManyToOne (optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id",nullable = false)
    private SalaEntity sala;







}
