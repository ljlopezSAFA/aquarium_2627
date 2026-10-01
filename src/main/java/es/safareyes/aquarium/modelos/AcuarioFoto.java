package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "acuario_foto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcuarioFoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_acuario", nullable = false)
    private Acuario acuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_diario_entrada")
    private DiarioEntrada diarioEntrada;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tomada_en", nullable = false)
    private LocalDateTime tomadaEn = LocalDateTime.now();
}
