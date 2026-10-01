package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.Dificultad;
import es.safareyes.aquarium.modelos.catalogos.TipoAgua;
import es.safareyes.aquarium.modelos.catalogos.TipoOrganismo;
import jakarta.persistence.*;
import java.math.BigDecimal;
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
@Table(name = "especie", schema = "acuarofilia")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_organismo", nullable = false)
    private TipoOrganismo tipoOrganismo;

    @Column(name = "nombre_comun", nullable = false, length = 150)
    private String nombreComun;

    @Column(name = "nombre_cientifico", nullable = false, unique = true, length = 150)
    private String nombreCientifico;

    @Column(name = "slug", nullable = false, unique = true, length = 160)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_familia")
    private Familia familia;

    // En BD: DEFAULT 1 (DULCE). Asígnalo en el servicio antes de guardar.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_agua", nullable = false)
    private TipoAgua tipoAgua;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dificultad", nullable = false)
    private Dificultad dificultad;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "temperatura_min", precision = 4, scale = 1)
    private BigDecimal temperaturaMin;

    @Column(name = "temperatura_max", precision = 4, scale = 1)
    private BigDecimal temperaturaMax;

    @Column(name = "ph_min", precision = 3, scale = 1)
    private BigDecimal phMin;

    @Column(name = "ph_max", precision = 3, scale = 1)
    private BigDecimal phMax;

    @Column(name = "gh_min", precision = 4, scale = 1)
    private BigDecimal ghMin;

    @Column(name = "gh_max", precision = 4, scale = 1)
    private BigDecimal ghMax;

    @Column(name = "kh_min", precision = 4, scale = 1)
    private BigDecimal khMin;

    @Column(name = "kh_max", precision = 4, scale = 1)
    private BigDecimal khMax;

    @Column(name = "publicada", nullable = false)
    private Boolean publicada = false;

    @Column(name = "fuentes")
    private String fuentes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por")
    private Usuario creadoPor;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

    @OneToMany(mappedBy = "especie")
    private List<EspecieImagen> imagenes = new ArrayList<>();

    @OneToMany(mappedBy = "especie")
    private List<EspecieNombreAlternativo> nombresAlternativos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "especie_biotopo",
            joinColumns = @JoinColumn(name = "id_especie"),
            inverseJoinColumns = @JoinColumn(name = "id_biotopo"))
    private List<Biotopo> biotopos = new ArrayList<>();
}
