package es.safareyes.aquarium.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EspeciesPorFamilia {
    private String familia;
    private Long especies;
}
