package es.safareyes.aquarium.controladores;


import es.safareyes.aquarium.dtos.EspecieDTO;
import es.safareyes.aquarium.servicios.EspecieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/especies")
@AllArgsConstructor
public class EspecieController {


    private EspecieService especieService;


    @GetMapping("/todas")
    public List<EspecieDTO> consultarEspecies(){
        return especieService.consultarTodas();
    }

    @GetMapping("/id")
    public List<EspecieDTO> consultarPorId(){
        return especieService.consultarTodas();
    }


}
