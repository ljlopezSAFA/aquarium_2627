package es.safareyes.aquarium.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;


public record CrearEspeciePezDTO(

        // --- Campos heredados de Especie ---
        @NotNull(message = "El tipo de organismo es obligatorio")
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

        @NotNull(message = "El tipo de agua es obligatorio")
        Short idTipoAgua,

        @NotNull(message = "La dificultad es obligatoria")
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
        @NotNull(message = "El tamaño máximo es obligatorio")
        @Digits(integer = 4, fraction = 1)
        @Positive(message = "El tamaño máximo debe ser positivo")
        BigDecimal tamanoMaximoCm,

        @Digits(integer = 3, fraction = 1)
        @Positive(message = "La esperanza de vida debe ser positiva")
        BigDecimal esperanzaVidaAnos,

        @NotNull(message = "El volumen mínimo es obligatorio")
        @Min(value = 1, message = "El volumen mínimo debe ser de al menos 1 litro")
        Integer volumenMinimoL,

        @Min(value = 1, message = "El largo mínimo debe ser positivo")
        Integer largoMinimoAcuarioCm,

        @NotNull(message = "La dieta es obligatoria")
        Short idDieta,

        @NotNull(message = "El temperamento es obligatorio")
        Short idTemperamento,

        @NotNull(message = "El nivel de nado es obligatorio")
        Short idNivelNado,

        Boolean esCardumen,

        @NotNull(message = "El tamaño mínimo de grupo es obligatorio")
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
        // Constructor compacto para asignar valores por defecto a booleanos y listas
        public CrearEspeciePezDTO {
                if (publicada == null) publicada = false;
                if (idsBiotopos == null) idsBiotopos = List.of();
                if (esCardumen == null) esCardumen = false;
                if (tamanoMinimoGrupo == null) tamanoMinimoGrupo = 1;
                if (comePlantas == null) comePlantas = false;
                if (comeInvertebrados == null) comeInvertebrados = false;
                if (esSaltador == null) esSaltador = false;
        }
}
