package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=PEZ, 2=PLANTA, 3=INVERTEBRADO */
@Entity
@Table(name = "cat_tipo_organismo")
@NoArgsConstructor
public class TipoOrganismo extends Catalogo {
}
