package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=MUY_LENTA, 2=LENTA, 3=MEDIA, 4=RAPIDA */
@Entity
@Table(name = "cat_tasa_crecimiento")
@NoArgsConstructor
public class TasaCrecimiento extends Catalogo {
}
