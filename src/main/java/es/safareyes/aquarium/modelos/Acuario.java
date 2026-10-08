package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.EstadoAcuario;
import es.safareyes.aquarium.modelos.catalogos.EstiloAcuario;
import es.safareyes.aquarium.modelos.catalogos.TipoAgua;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "acuario", schema = "acuarofilia")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Acuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    // En BD: DEFAULT 1 (DULCE). Asígnalo en el servicio antes de guardar.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_agua", nullable = false)
    private TipoAgua tipoAgua;

    // En BD: DEFAULT 1 (COMUNITARIO). Asígnalo en el servicio antes de guardar.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estilo", nullable = false)
    private EstiloAcuario estilo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_biotopo")
    private Biotopo biotopo;

    @Column(name = "volumen_bruto_l")
    private Integer volumenBrutoL;

    @Column(name = "volumen_neto_l", nullable = false)
    private Integer volumenNetoL;

    @Column(name = "largo_cm")
    private Integer largoCm;

    @Column(name = "ancho_cm")
    private Integer anchoCm;

    @Column(name = "alto_cm")
    private Integer altoCm;

    @Column(name = "fecha_montaje", nullable = false)
    private LocalDate fechaMontaje;

    @Column(name = "fecha_desmontaje")
    private LocalDate fechaDesmontaje;

    // En BD: DEFAULT 1 (CICLANDO). Asígnalo en el servicio antes de guardar.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoAcuario estado;

    @Column(name = "con_co2", nullable = false)
    private Boolean conCo2 = false;

    @Column(name = "fotoperiodo_horas", precision = 3, scale = 1)
    private BigDecimal fotoperiodoHoras;

    @Column(name = "foto_url", length = 500)
    private String fotoUrl;

    @Column(name = "notas")
    private String notas;

    @Column(name = "es_publico", nullable = false)
    private Boolean esPublico = false;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

    @OneToMany(mappedBy = "acuario")
    private List<AcuarioHabitante> habitantes = new ArrayList<>();



    //Int (0,1,2,3)
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_cristal")
    private TipoCristal tipoCristal;


}
