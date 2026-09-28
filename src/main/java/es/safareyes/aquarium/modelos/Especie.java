package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "especie")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_organismo")
    private TipoOrganismo tipoOrganismo;

    @Column(name = "nombre_comun")
    private String nombreComun;

    @Column(name = "nombre_cientifico")
    private String nombreCientifico;

    @Column(name = "slug")
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_familia")
    private Familia familia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_agua")
    private TipoAgua tipoAgua;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dificultad")
    private Dificultad dificultad;

    @Column
    private String descripcion;

    @Column(name = "temperatura_min", precision = 4, scale = 1)
    private BigDecimal temperaturaMin;

    @Column(name = "temperatura_max", precision = 4, scale = 1)
    private BigDecimal temperaturaMax;

    @Column(name = "ph_min", precision = 4, scale = 1)
    private BigDecimal phMin;

    @Column(name = "ph_max")
    private BigDecimal phMax;

    @Column(name = "gh_max", precision = 4, scale = 1)
    private BigDecimal ghMax;

    @Column(name = "gh_min", precision = 4, scale = 1)
    private BigDecimal ghMin;

    @Column(name = "kh_min", precision = 4, scale = 1)
    private BigDecimal khMin;

    @Column(name = "kh_max", precision = 4, scale = 1)
    private BigDecimal khMax;

    @Column(name = "publicada")
    private Boolean publicada;

    @Column(name = "fuentes")
    private String fuentes;

    @CreatedDate
    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;


}
