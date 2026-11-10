package es.safareyes.aquarium.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;


public record EditarEspeciePezDTO(


        Short idTipoOrganismo,

        @NotBlank(message = "El nombre común no puede estar vacío")
        @Size(max = 150, message = "El nombre común no debe superar los 150 caracteres")
        String nombreComun,

        @NotBlank(message = "El nombre científico no puede estar vacío")
        @Size(max = 150, message = "El nombre científico no debe superar los 150 caracteres")
        String nombreCientifico,

        @NotBlank(message = "El slug no puede estar vacío")
        @Size(max = 160, message = "El slug no debe superar los 160 caracteres")
        String slug,

        Integer idFamilia,
        Short idTipoAgua,
        Short idDificultad,

        @NotBlank(message = "La descripción no puede estar vacía")
        String descripcion,

        @Digits(integer = 3, fraction = 1)
        BigDecimal temperaturaMin,

        @Digits(integer = 3, fraction = 1)
        BigDecimal temperaturaMax,

        @Digits(integer = 2, fraction = 1)
        BigDecimal phMin,

        @Digits(integer = 2, fraction = 1)
        BigDecimal phMax,

        @Digits(integer = 3, fraction = 1)
        BigDecimal ghMin,

        @Digits(integer = 3, fraction = 1)
        BigDecimal ghMax,

        @Digits(integer = 3, fraction = 1)
        BigDecimal khMin,

        @Digits(integer = 3, fraction = 1)
        BigDecimal khMax,

        Boolean publicada,

        String fuentes,

        Integer idCreadoPor,

        List<Short> idsBiotopos,

        // --- Campos específicos de EspeciePez ---
        @Digits(integer = 4, fraction = 1)
        @Positive(message = "El tamaño máximo debe ser positivo")
        BigDecimal tamanoMaximoCm,

        @Digits(integer = 3, fraction = 1)
        @Positive(message = "La esperanza de vida debe ser positiva")
        BigDecimal esperanzaVidaAnos,

        @Min(value = 1, message = "El volumen mínimo debe ser de al menos 1 litro")
        Integer volumenMinimoL,

        @Min(value = 1, message = "El largo mínimo debe ser positivo")
        Integer largoMinimoAcuarioCm,

        Short idDieta,
        Short idTemperamento,
        Short idNivelNado,

        Boolean esCardumen,

        @Min(value = 1, message = "El tamaño mínimo de grupo debe ser al menos 1")
        Short tamanoMinimoGrupo,

        Boolean comePlantas,

        Boolean comeInvertebrados,

        Boolean esSaltador,

        Short idTipoReproduccion,

        Short idDificultadReproduccion,

        String dimorfismoSexual,

        String notasReproduccion,

        Short idEstadoConservacion

) {
}
