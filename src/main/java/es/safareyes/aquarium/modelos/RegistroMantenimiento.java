package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.TipoMantenimiento;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "registro_mantenimiento")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroMantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acuario", nullable = false)
    private Acuario acuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarea")
    private TareaProgramada tarea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoMantenimiento tipo;

    @Column(name = "realizado_en", nullable = false)
    private LocalDateTime realizadoEn = LocalDateTime.now();

    @Column(name = "porcentaje_cambio_agua")
    private Short porcentajeCambioAgua;

    @Column(name = "producto", length = 150)
    private String producto;

    @Column(name = "dosis", length = 50)
    private String dosis;

    @Column(name = "notas")
    private String notas;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;
}
