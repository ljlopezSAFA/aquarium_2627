package es.safareyes.aquarium.controladores;


import es.safareyes.aquarium.dtos.CrearFamiliaDTO;
import es.safareyes.aquarium.dtos.FamiliaDTO;
import es.safareyes.aquarium.servicios.FamiliaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/filtrar")
    public List<FamiliaDTO> filtrarFamilias(@RequestParam(required = false) String nombre,
                                            @RequestParam(required = false) String orden){
        return service.filtrar(nombre, orden);
    }

    @PostMapping("/crear")
    public void crearFamilia(@Valid @RequestBody CrearFamiliaDTO dto){
       service.crearFamilia(dto);
    }


    @DeleteMapping("/{id}")
    public void eliminarFamilia(@PathVariable Integer id){
        service.eliminarFamilia(id);
    }


    @PatchMapping("/{id}")
    public void eliminarFamiliaLogico(@PathVariable Integer id){
        service.eliminarFamiliaLogico(id);
    }



}
