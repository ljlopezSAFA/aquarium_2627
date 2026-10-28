package es.safareyes.aquarium.controladores;

import es.safareyes.aquarium.modelos.catalogos.Continente;
import es.safareyes.aquarium.servicios.ContinenteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/continente")
@AllArgsConstructor
public class ContinenteController {

    private ContinenteService service;

    @GetMapping
    public List<Continente> consultarContinentes(){
       return service.consultarTodosContinetes();
    }



}
