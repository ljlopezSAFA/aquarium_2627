package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=GAMBA, 2=CANGREJO, 3=CANGREJO_RIO, 4=CARACOL, 5=BIVALVO, 6=OTRO */
@Entity
@Table(name = "cat_grupo_invertebrado")
@NoArgsConstructor
public class GrupoInvertebrado extends Catalogo {
}
