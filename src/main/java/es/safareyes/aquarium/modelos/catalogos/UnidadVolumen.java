package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=L, 2=GAL */
@Entity
@Table(name = "cat_unidad_volumen")
@NoArgsConstructor
public class UnidadVolumen extends Catalogo {
}
