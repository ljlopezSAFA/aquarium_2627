package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.NivelLuz;
import es.safareyes.aquarium.modelos.catalogos.PosicionPlanta;
import es.safareyes.aquarium.modelos.catalogos.TasaCrecimiento;
import es.safareyes.aquarium.modelos.catalogos.TipoPlantado;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "especie_planta")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
@NoArgsConstructor
public class EspeciePlanta extends Especie {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_plantado", nullable = false)
    private TipoPlantado tipoPlantado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_posicion")
    private PosicionPlanta posicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_requerimiento_luz", nullable = false)
    private NivelLuz requerimientoLuz;

    @Column(name = "requiere_co2", nullable = false)
    private Boolean requiereCo2 = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tasa_crecimiento", nullable = false)
    private TasaCrecimiento tasaCrecimiento;

    @Column(name = "altura_min_cm", precision = 5, scale = 1)
    private BigDecimal alturaMinCm;

    @Column(name = "altura_max_cm", precision = 5, scale = 1)
    private BigDecimal alturaMaxCm;

    @Column(name = "color_predominante", length = 30)
    private String colorPredominante;

    @Column(name = "requiere_abono_raiz", nullable = false)
    private Boolean requiereAbonoRaiz = false;

    @Column(name = "puede_cultivarse_emersa", nullable = false)
    private Boolean puedeCultivarseEmersa = false;

    @Column(name = "metodo_propagacion", length = 150)
    private String metodoPropagacion;
}
