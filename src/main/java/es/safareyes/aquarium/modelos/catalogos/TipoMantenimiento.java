package es.safareyes.aquarium.modelos.catalogos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

/** Opciones iniciales: 1=CAMBIO_AGUA, 2=LIMPIEZA_FILTRO, 3=CAMBIO_MATERIAL_FILTRANTE, 4=LIMPIEZA_CRISTALES, 5=SIFONADO, 6=PODA, 7=ABONADO, 8=RECARGA_CO2, 9=TEST_AGUA, 10=ALIMENTACION, 11=TRATAMIENTO, 12=OTRO */
@Entity
@Table(name = "cat_tipo_mantenimiento")
@NoArgsConstructor
public class TipoMantenimiento extends Catalogo {
}
