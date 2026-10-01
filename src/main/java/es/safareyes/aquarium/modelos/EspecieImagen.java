package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "especie_imagen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EspecieImagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "autor", length = 150)
    private String autor;

    @Column(name = "licencia", length = 50)
    private String licencia;

    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal = false;

    @Column(name = "orden", nullable = false)
    private Short orden = 0;
}
