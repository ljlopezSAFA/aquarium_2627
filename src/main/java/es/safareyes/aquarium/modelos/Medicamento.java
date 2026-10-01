package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medicamento", uniqueConstraints = {
        @UniqueConstraint(name = "ux_medicamento_nombre_marca", columnNames = {"nombre", "id_marca"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "principio_activo", length = 150)
    private String principioActivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "seguro_invertebrados", nullable = false)
    private Boolean seguroInvertebrados = false;

    @Column(name = "seguro_plantas", nullable = false)
    private Boolean seguroPlantas = true;

    @Column(name = "afecta_filtro_biologico", nullable = false)
    private Boolean afectaFiltroBiologico = false;

    @Column(name = "contiene_cobre", nullable = false)
    private Boolean contieneCobre = false;
}
