package es.safareyes.aquarium.controladores;


import es.safareyes.aquarium.dtos.FamiliaDTO;
import es.safareyes.aquarium.servicios.FamiliaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/familias")
@AllArgsConstructor
public class FamiliaController {

    private FamiliaService service;

    @GetMapping
    public List<FamiliaDTO> consultarFamilias(){
        return service.consultarTodas();
    }



}
