package cine.Pelicula;

import cine.funcion.FuncionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder
@Entity
@Table (name = "pelicula")
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;


    @Column(unique = true,nullable = false)
    private String cod;

    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private Integer duracion;
    @Column(length = 1000)
    private String sinopsis;

    @OneToMany(mappedBy = "pelicula")
    private List<FuncionEntity> funciones = new ArrayList<>();

    @OneToMany(mappedBy = "pelicula",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<PeliculaGeneroEntity> peliculaGeneros= new HashSet<>();

}
