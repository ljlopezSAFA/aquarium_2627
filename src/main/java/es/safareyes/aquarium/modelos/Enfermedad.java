package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.AgenteEnfermedad;
import es.safareyes.aquarium.modelos.catalogos.Gravedad;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "enfermedad")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enfermedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = true, length = 150)
    private String nombre;

    @Column(name = "nombre_tecnico", length = 150)
    private String nombreTecnico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agente", nullable = false)
    private AgenteEnfermedad agente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gravedad", nullable = false)
    private Gravedad gravedad;

    @Column(name = "es_contagiosa", nullable = false)
    private Boolean esContagiosa = false;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "causas")
    private String causas;

    @Column(name = "tratamiento")
    private String tratamiento;

    @Column(name = "prevencion")
    private String prevencion;

    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

    @OneToMany(mappedBy = "enfermedad")
    private List<EnfermedadSintoma> sintomas = new ArrayList<>();

    @OneToMany(mappedBy = "enfermedad")
    private List<EnfermedadMedicamento> medicamentos = new ArrayList<>();
}
