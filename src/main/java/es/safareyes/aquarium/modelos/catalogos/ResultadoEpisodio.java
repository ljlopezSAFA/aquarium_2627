package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=EN_CURSO, 2=RECUPERADO, 3=FALLECIDO, 4=CRONICO */
@Entity
@Table(name = "cat_resultado_episodio")
@NoArgsConstructor
public class ResultadoEpisodio extends Catalogo {
}
