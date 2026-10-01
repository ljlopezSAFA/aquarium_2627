package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.ResultadoEpisodio;
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
@Table(name = "episodio_salud")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EpisodioSalud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acuario", nullable = false)
    private Acuario acuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie")
    private Especie especie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enfermedad")
    private Enfermedad enfermedad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio = LocalDate.now();

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "individuos_afectados")
    private Short individuosAfectados;

    @Column(name = "individuos_fallecidos")
    private Short individuosFallecidos;

    @Column(name = "tratamiento_aplicado")
    private String tratamientoAplicado;

    // En BD: DEFAULT 1 (EN_CURSO). Asígnalo en el servicio antes de guardar.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_resultado", nullable = false)
    private ResultadoEpisodio resultado;

    @Column(name = "notas")
    private String notas;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;
}
