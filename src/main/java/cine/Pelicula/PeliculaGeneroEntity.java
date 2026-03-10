package cine.Pelicula;

import cine.genero.GeneroEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Getter
@Entity
@Table(name = "pelicula_genero",
        uniqueConstraints = @UniqueConstraint (columnNames ={"pelicula_id","genero_id"})
)

public class PeliculaGeneroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_id",nullable = false)
    private PeliculaEntity pelicula;

    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="genero_id",nullable = false)
    private GeneroEntity genero;

}
