package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=DULCE, 2=SALOBRE, 3=MARINA */
@Entity
@Table(name = "cat_tipo_agua")
@NoArgsConstructor
public class TipoAgua extends Catalogo {
}
