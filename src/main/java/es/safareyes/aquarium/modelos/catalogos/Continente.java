package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=AMERICA_SUR, 2=AMERICA_CENTRAL, 3=AMERICA_NORTE, 4=AFRICA, 5=ASIA, 6=OCEANIA, 7=EUROPA, 8=CULTIVO */
@Entity
@Table(name = "cat_continente", schema = "acuarofilia")
@NoArgsConstructor
public class Continente extends Catalogo {
}
