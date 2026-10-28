package es.safareyes.aquarium.dtos;

public record CrearFamiliaDTO(
        Integer id, String nombre, String descripcion, String orden, Short idOrganismo
) {
}
