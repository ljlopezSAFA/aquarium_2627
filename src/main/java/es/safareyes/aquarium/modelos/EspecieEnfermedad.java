package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "especie_enfermedad", uniqueConstraints = {
        @UniqueConstraint(name = "ux_especie_enfermedad", columnNames = {"id_especie", "id_enfermedad"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspecieEnfermedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enfermedad", nullable = false)
    private Enfermedad enfermedad;

    @Column(name = "notas")
    private String notas;
}
