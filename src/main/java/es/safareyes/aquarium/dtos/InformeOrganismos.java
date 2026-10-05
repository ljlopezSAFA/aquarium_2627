package es.safareyes.aquarium.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InformeOrganismos {
    private String tipo_organismo;
    private Long recuento;
}
