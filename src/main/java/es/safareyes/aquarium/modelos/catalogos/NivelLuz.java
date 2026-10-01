package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=BAJA, 2=MEDIA, 3=ALTA */
@Entity
@Table(name = "cat_nivel_luz")
@NoArgsConstructor
public class NivelLuz extends Catalogo {
}
