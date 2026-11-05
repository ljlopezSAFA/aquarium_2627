package es.safareyes.aquarium.controladores;


import es.safareyes.aquarium.excepciones.ReglaNegocioException;
import es.safareyes.aquarium.excepciones.RelacionNoEncontrada;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ErrorGlobalController {


    @ExceptionHandler(ReglaNegocioException.class)
    public ProblemDetail reglaNegocia(ReglaNegocioException e) {
        return construir(HttpStatus.NOT_FOUND, "Regla de negocio no cumplida",
                e.getMessage());
    }

    @ExceptionHandler(RelacionNoEncontrada.class)
    public ProblemDetail relacionNoEncantrada(RelacionNoEncontrada e) {
        return construir(HttpStatus.NOT_FOUND, "Regla de negocio no cumplida",
                e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail erroresDTO(MethodArgumentNotValidException e) {
        Map<String, String> errores = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));


        ProblemDetail problema = construir(HttpStatus.BAD_REQUEST,
                "Datos no válidos", "Revisa los campos indicados");
        problema.setProperty("errores", errores);
        return problema;
    }


    private ProblemDetail construir(HttpStatus estado, String titulo, String detalle) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(estado, detalle);
        problema.setTitle(titulo);
        problema.setProperty("timestamp", LocalDateTime.now());
        return problema;
    }


}
