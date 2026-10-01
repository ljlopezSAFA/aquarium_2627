package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "acuario_equipamiento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcuarioEquipamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acuario", nullable = false)
    private Acuario acuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_equipamiento")
    private Equipamiento equipamiento;

    @Column(name = "descripcion_libre", length = 150)
    private String descripcionLibre;

    @Column(name = "cantidad", nullable = false)
    private Short cantidad = 1;

    @Column(name = "fecha_instalacion")
    private LocalDate fechaInstalacion;

    @Column(name = "fecha_retirada")
    private LocalDate fechaRetirada;

    @Column(name = "notas")
    private String notas;
}
