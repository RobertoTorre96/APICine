package cine.genero;

import cine.Pelicula.PeliculaGeneroEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class GeneroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private  Long id;
    @Column(unique = true,nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "genero")
    private Set<PeliculaGeneroEntity> peliculaGeneros=new HashSet<>();

}


