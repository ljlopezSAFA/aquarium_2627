package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=FAVORITO, 2=DESEO */
@Entity
@Table(name = "cat_lista_guardada")
@NoArgsConstructor
public class ListaGuardada extends Catalogo {
}
