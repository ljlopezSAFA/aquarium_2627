package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=ESCAMAS, 2=GRANULADO, 3=PASTILLA, 4=CONGELADO, 5=VIVO, 6=LIOFILIZADO, 7=VEGETAL_FRESCO, 8=GEL, 9=OTRO */
@Entity
@Table(name = "cat_tipo_alimento")
@NoArgsConstructor
public class TipoAlimento extends Catalogo {
}
