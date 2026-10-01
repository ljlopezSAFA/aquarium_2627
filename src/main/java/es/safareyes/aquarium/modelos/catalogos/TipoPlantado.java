package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=TAPIZANTE, 2=ENTERRADA, 3=RIZOMA, 4=FLOTANTE, 5=MUSGO, 6=BULBO, 7=EMERGIDA */
@Entity
@Table(name = "cat_tipo_plantado")
@NoArgsConstructor
public class TipoPlantado extends Catalogo {
}
