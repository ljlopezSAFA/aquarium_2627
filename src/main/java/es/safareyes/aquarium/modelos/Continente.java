package es.safareyes.aquarium.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cat_continente")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Continente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "orden")
    private Short orden;

    @Column(name = "activo")
    private Boolean activo;



}
