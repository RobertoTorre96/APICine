package cine.reserva;


import cine.asiento.AsientoEntity;
import cine.funcion.FuncionEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "reserva_asientos",
        uniqueConstraints = @UniqueConstraint(columnNames = {"funcion_id", "asiento_id"}
)
)
public class ReservaAsientoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne (optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "asiento_id",nullable = false)
    private AsientoEntity asiento;

    @ManyToOne (optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id",nullable = false)
    private ReservaEntity reserva;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "funcion_id", nullable = false)
    private FuncionEntity funcion;

}
