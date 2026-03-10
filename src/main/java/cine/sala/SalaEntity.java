package cine.sala;


import cine.asiento.AsientoEntity;
import cine.funcion.FuncionEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "sala")
public class SalaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String cod;


    @OneToMany (mappedBy = "sala",
    cascade =  CascadeType.ALL
    ,orphanRemoval = true)
    private List<AsientoEntity> asientos=new ArrayList<>();

    @OneToMany(mappedBy = "sala")
    private  List<FuncionEntity> funciones=new ArrayList<>();


}
