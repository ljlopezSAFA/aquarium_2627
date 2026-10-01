package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "enfermedad_sintoma", uniqueConstraints = {
        @UniqueConstraint(name = "ux_enfermedad_sintoma", columnNames = {"id_enfermedad", "id_sintoma"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnfermedadSintoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enfermedad", nullable = false)
    private Enfermedad enfermedad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sintoma", nullable = false)
    private Sintoma sintoma;

    @Column(name = "es_caracteristico", nullable = false)
    private Boolean esCaracteristico = false;
}
