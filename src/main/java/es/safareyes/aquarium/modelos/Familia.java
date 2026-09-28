package es.safareyes.aquarium.modelos;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "familia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "nombre")
    private String nombre;


    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "orden")
    private String orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_organismo")
    private TipoOrganismo tipoOrganismo;



}
