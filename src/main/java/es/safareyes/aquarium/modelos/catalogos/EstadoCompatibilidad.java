package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=IDEAL, 2=PRECAUCION, 3=INCOMPATIBLE */
@Entity
@Table(name = "cat_estado_compatibilidad")
@NoArgsConstructor
public class EstadoCompatibilidad extends Catalogo {
}
