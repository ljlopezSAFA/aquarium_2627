package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.MotivoBaja;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "acuario_habitante")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcuarioHabitante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acuario", nullable = false)
    private Acuario acuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "cantidad_descripcion", length = 50)
    private String cantidadDescripcion;

    @Column(name = "variedad", length = 100)
    private String variedad;

    @Column(name = "machos")
    private Short machos;

    @Column(name = "hembras")
    private Short hembras;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta = LocalDate.now();

    @Column(name = "fecha_baja")
    private LocalDate fechaBaja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_motivo_baja")
    private MotivoBaja motivoBaja;

    @Column(name = "notas")
    private String notas;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;
}
