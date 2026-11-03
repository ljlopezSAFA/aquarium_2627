package es.safareyes.aquarium.dtos;

import java.util.List;

public record EspecieDTO (
    Integer id,
    String nombreComun,
    String nombreCientifico,
    String descripcion,
    String dificultad,
    FamiliaDTO familia,
    List<BiotopoDTO> biotopos
){}

