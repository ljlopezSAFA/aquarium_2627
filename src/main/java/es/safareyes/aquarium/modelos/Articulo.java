package es.safareyes.aquarium.modelos;

import es.safareyes.aquarium.modelos.catalogos.CategoriaArticulo;
import jakarta.persistence.*;
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
@Table(name = "articulo")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "slug", nullable = false, unique = true, length = 220)
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaArticulo categoria;

    @Column(name = "resumen", length = 500)
    private String resumen;

    @Column(name = "contenido_md", nullable = false)
    private String contenidoMd;

    @Column(name = "imagen_portada_url", length = 500)
    private String imagenPortadaUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Usuario autor;

    @Column(name = "publicado", nullable = false)
    private Boolean publicado = false;

    @Column(name = "publicado_en")
    private LocalDateTime publicadoEn;

    @CreatedDate
    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @LastModifiedDate
    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

    @ManyToMany
    @JoinTable(name = "articulo_especie",
            joinColumns = @JoinColumn(name = "id_articulo"),
            inverseJoinColumns = @JoinColumn(name = "id_especie"))
    private List<Especie> especies = new ArrayList<>();
}
