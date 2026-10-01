package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.Dieta;
import es.safareyes.aquarium.modelos.catalogos.Dificultad;
import es.safareyes.aquarium.modelos.catalogos.EstadoConservacion;
import es.safareyes.aquarium.modelos.catalogos.NivelNado;
import es.safareyes.aquarium.modelos.catalogos.Temperamento;
import es.safareyes.aquarium.modelos.catalogos.TipoReproduccion;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "especie_pez")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class EspeciePez extends Especie {

    @Column(name = "tamano_maximo_cm", nullable = false, precision = 5, scale = 1)
    private BigDecimal tamanoMaximoCm;

    @Column(name = "esperanza_vida_anos", precision = 4, scale = 1)
    private BigDecimal esperanzaVidaAnos;

    @Column(name = "volumen_minimo_l", nullable = false)
    private Integer volumenMinimoL;

    @Column(name = "largo_minimo_acuario_cm")
    private Integer largoMinimoAcuarioCm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dieta", nullable = false)
    private Dieta dieta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_temperamento", nullable = false)
    private Temperamento temperamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_nado", nullable = false)
    private NivelNado nivelNado;

    @Column(name = "es_cardumen", nullable = false)
    private Boolean esCardumen = false;

    @Column(name = "tamano_minimo_grupo", nullable = false)
    private Short tamanoMinimoGrupo = 1;

    @Column(name = "come_plantas", nullable = false)
    private Boolean comePlantas = false;

    @Column(name = "come_invertebrados", nullable = false)
    private Boolean comeInvertebrados = false;

    @Column(name = "es_saltador", nullable = false)
    private Boolean esSaltador = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_reproduccion")
    private TipoReproduccion tipoReproduccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dificultad_reproduccion")
    private Dificultad dificultadReproduccion;

    @Column(name = "dimorfismo_sexual")
    private String dimorfismoSexual;

    @Column(name = "notas_reproduccion")
    private String notasReproduccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_conservacion")
    private EstadoConservacion estadoConservacion;
}
