package cine.funcion;

import cine.Pelicula.PeliculaEntity;
import cine.reserva.ReservaEntity;
import cine.sala.SalaEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"sala_id","fecha_hora"})
)
public class FuncionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "pelicula_id",nullable = false)
    private PeliculaEntity pelicula;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id",nullable = false)
    private SalaEntity sala;

    @OneToMany(mappedBy = "funcion",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    private List<ReservaEntity>reservas=new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(name = "fecha_hora",nullable = false)
    private LocalDateTime fechaHora;

    @Version
    private Long version;




}
