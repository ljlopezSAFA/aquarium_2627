package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "especie_alimento", uniqueConstraints = {
        @UniqueConstraint(name = "ux_especie_alimento", columnNames = {"id_especie", "id_alimento"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspecieAlimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alimento", nullable = false)
    private Alimento alimento;

    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal = false;

    @Column(name = "frecuencia", length = 100)
    private String frecuencia;
}
