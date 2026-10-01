package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=PRIMEROS_PASOS, 2=CICLADO, 3=QUIMICA_AGUA, 4=PLANTAS, 5=AQUASCAPING, 6=REPRODUCCION, 7=SALUD, 8=EQUIPAMIENTO, 9=ALIMENTACION, 10=BIOTOPOS */
@Entity
@Table(name = "cat_categoria_articulo")
@NoArgsConstructor
public class CategoriaArticulo extends Catalogo {
}
