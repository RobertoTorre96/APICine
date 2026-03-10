package cine.reserva;

import cine.funcion.FuncionEntity;
import cine.usuario.UsuarioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "reserva")
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "funcion_id",nullable = false)
    private FuncionEntity funcion;

    @OneToMany(mappedBy = "reserva",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ReservaAsientoEntity>asientos=new ArrayList<>();

    @ManyToOne (optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private LocalDateTime fecha;



}
