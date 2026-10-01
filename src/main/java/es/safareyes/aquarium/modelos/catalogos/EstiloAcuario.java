package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=COMUNITARIO, 2=BIOTOPO, 3=HOLANDES, 4=NATURAL, 5=IWAGUMI, 6=JUNGLA, 7=GAMBARIO, 8=NANO, 9=CRIA, 10=CUARENTENA, 11=PALUDARIO, 12=OTRO */
@Entity
@Table(name = "cat_estilo_acuario")
@NoArgsConstructor
public class EstiloAcuario extends Catalogo {
}
