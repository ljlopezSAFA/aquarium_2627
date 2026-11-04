package es.safareyes.aquarium.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearFamiliaDTO(
        Integer id,

        @NotBlank(message = "El nombre de la familia no puede estar vacío")
        @NotNull(message = "El nombre de la familia no puede ser null")
        String nombre,


        @NotBlank(message = "El nombre de la descripcion no puede estar vacío")
        @NotNull(message = "El nombre de la descripcion no puede ser null")
        String descripcion,

        @NotBlank(message = "El nombre de la orden no puede estar vacío")
        @NotNull(message = "El nombre de la orden no puede ser null")
        String orden,

        @Min(1)
        Short idOrganismo
) {
}
