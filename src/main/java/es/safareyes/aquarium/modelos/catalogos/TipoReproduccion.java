package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=OVIPARO_DISPERSOR, 2=OVIPARO_ADHERENTE, 3=NIDO_BURBUJAS, 4=INCUBADOR_BUCAL, 5=CUEVA, 6=VIVIPARO, 7=KILLI_ANUAL, 8=NO_REPRODUCIBLE_CAUTIVIDAD */
@Entity
@Table(name = "cat_tipo_reproduccion")
@NoArgsConstructor
public class TipoReproduccion extends Catalogo {
}
