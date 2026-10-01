package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.EstadoCompatibilidad;
import es.safareyes.aquarium.modelos.catalogos.MotivoCompatibilidad;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "compatibilidad", uniqueConstraints = {
        @UniqueConstraint(name = "ux_compatibilidad", columnNames = {"id_especie_a", "id_especie_b"})
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compatibilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie_a", nullable = false)
    private Especie especieA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie_b", nullable = false)
    private Especie especieB;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoCompatibilidad estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_motivo")
    private MotivoCompatibilidad motivo;

    @Column(name = "notas")
    private String notas;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;
}
