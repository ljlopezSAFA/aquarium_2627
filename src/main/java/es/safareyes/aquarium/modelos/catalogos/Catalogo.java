package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Campos comunes de todas las tablas cat_xxx.
 * @MappedSuperclass no es una tabla: sus campos se copian en cada entidad que la extiende.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "codigo", nullable = false, unique = true, length = 30)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "orden", nullable = false)
    private Short orden = 0;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;
}
