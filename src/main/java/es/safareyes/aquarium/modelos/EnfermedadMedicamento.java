package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enfermedad_medicamento", uniqueConstraints = {
        @UniqueConstraint(name = "ux_enfermedad_medicamento", columnNames = {"id_enfermedad", "id_medicamento"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnfermedadMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enfermedad", nullable = false)
    private Enfermedad enfermedad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medicamento", nullable = false)
    private Medicamento medicamento;

    @Column(name = "notas")
    private String notas;
}
