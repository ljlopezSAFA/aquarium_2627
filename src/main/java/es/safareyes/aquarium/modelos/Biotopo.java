package es.safareyes.aquarium.modelos;


import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "biotopo")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Biotopo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "imagen_url")
    private String urlImagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_continente")
    private Continente continente;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "especie_biotopo",
            joinColumns = @JoinColumn(name = "id_biotopo"),
            inverseJoinColumns = @JoinColumn(name = "id_especie")
    )
    private List<Especie> especie;



}
