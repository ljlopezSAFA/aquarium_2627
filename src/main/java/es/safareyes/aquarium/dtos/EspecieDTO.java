package es.safareyes.aquarium.dtos;

public record EspecieDTO (
    Integer id,
    String nombreComun,
    String nombreCientifico,
    String descripcion,
    FamiliaDTO familia
){}

