package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.MetodoMedicion;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "medicion_agua")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicionAgua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acuario", nullable = false)
    private Acuario acuario;

    @Column(name = "medido_en", nullable = false)
    private LocalDateTime medidoEn = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodo")
    private MetodoMedicion metodo;

    @Column(name = "temperatura", precision = 4, scale = 1)
    private BigDecimal temperatura;

    @Column(name = "ph", precision = 3, scale = 1)
    private BigDecimal ph;

    @Column(name = "gh", precision = 4, scale = 1)
    private BigDecimal gh;

    @Column(name = "kh", precision = 4, scale = 1)
    private BigDecimal kh;

    @Column(name = "amoniaco_mg_l", precision = 5, scale = 2)
    private BigDecimal amoniacoMgL;

    @Column(name = "nitrito_mg_l", precision = 5, scale = 2)
    private BigDecimal nitritoMgL;

    @Column(name = "nitrato_mg_l", precision = 5, scale = 1)
    private BigDecimal nitratoMgL;

    @Column(name = "fosfato_mg_l", precision = 5, scale = 2)
    private BigDecimal fosfatoMgL;

    @Column(name = "hierro_mg_l", precision = 5, scale = 2)
    private BigDecimal hierroMgL;

    @Column(name = "cobre_mg_l", precision = 5, scale = 2)
    private BigDecimal cobreMgL;

    @Column(name = "co2_mg_l", precision = 5, scale = 1)
    private BigDecimal co2MgL;

    @Column(name = "oxigeno_mg_l", precision = 4, scale = 1)
    private BigDecimal oxigenoMgL;

    @Column(name = "tds_ppm")
    private Integer tdsPpm;

    @Column(name = "conductividad_us_cm")
    private Integer conductividadUsCm;

    @Column(name = "densidad", precision = 5, scale = 4)
    private BigDecimal densidad;

    @Column(name = "notas")
    private String notas;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;
}
