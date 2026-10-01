package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.Dieta;
import es.safareyes.aquarium.modelos.catalogos.GrupoInvertebrado;
import es.safareyes.aquarium.modelos.catalogos.Temperamento;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "especie_invertebrado")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class EspecieInvertebrado extends Especie {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo", nullable = false)
    private GrupoInvertebrado grupo;

    @Column(name = "tamano_maximo_cm", nullable = false, precision = 5, scale = 1)
    private BigDecimal tamanoMaximoCm;

    @Column(name = "esperanza_vida_anos", precision = 4, scale = 1)
    private BigDecimal esperanzaVidaAnos;

    @Column(name = "volumen_minimo_l", nullable = false)
    private Integer volumenMinimoL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dieta", nullable = false)
    private Dieta dieta;

    // En BD: DEFAULT 1 (PACIFICO). Asígnalo en el servicio antes de guardar.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_temperamento", nullable = false)
    private Temperamento temperamento;

    @Column(name = "come_plantas", nullable = false)
    private Boolean comePlantas = false;

    @Column(name = "sensible_cobre", nullable = false)
    private Boolean sensibleCobre = true;

    @Column(name = "se_reproduce_agua_dulce", nullable = false)
    private Boolean seReproduceAguaDulce = true;

    @Column(name = "tamano_minimo_grupo", nullable = false)
    private Short tamanoMinimoGrupo = 1;

    @Column(name = "notas_muda")
    private String notasMuda;
}
