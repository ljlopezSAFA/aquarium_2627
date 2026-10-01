package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "equipamiento")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEquipamiento categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Column(name = "modelo", nullable = false, length = 150)
    private String modelo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "es_natural", nullable = false)
    private Boolean esNatural = false;

    @Column(name = "altera_parametros", length = 100)
    private String alteraParametros;

    @Column(name = "caudal_l_h")
    private Integer caudalLH;

    @Column(name = "potencia_w", precision = 6, scale = 1)
    private BigDecimal potenciaW;

    @Column(name = "volumen_acuario_min_l")
    private Integer volumenAcuarioMinL;

    @Column(name = "volumen_acuario_max_l")
    private Integer volumenAcuarioMaxL;

    @Column(name = "especificaciones")
    private String especificaciones;

    @Column(name = "imagen_url", length = 500)
    private String imagenUrl;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;
}
