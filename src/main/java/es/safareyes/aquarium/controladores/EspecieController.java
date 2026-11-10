package es.safareyes.aquarium.controladores;


import es.safareyes.aquarium.dtos.CrearEspeciePezDTO;
import es.safareyes.aquarium.dtos.EditarEspeciePezDTO;
import es.safareyes.aquarium.dtos.EspecieDTO;
import es.safareyes.aquarium.servicios.EspecieService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/filtrar")
    public List<EspecieDTO> consultarConFiltros(
            @RequestParam(required = false)  String nombre,
            @RequestParam(required = false) String familia,
            @RequestParam(required = false) String dificultad){
        return especieService.filtrar(nombre, familia, dificultad);
    }

    @PostMapping("/crear")
    public void crearEspecie(@Valid  @RequestBody CrearEspeciePezDTO json){
        especieService.crearEspeciePez(json);
    }

    @DeleteMapping("/{id}")
    public void eliminarEspecie(@PathVariable Integer id){
        especieService.eliminar(id);
    }

    @PatchMapping("/{id}")
    public void eliminarEspecieLogico(@PathVariable Integer id){
        especieService.cambiarEstadoEspecie(id);
    }


    @PutMapping("/{id}")
    public void editarEspecie(@PathVariable Integer id,
                              @RequestBody EditarEspeciePezDTO dto){

        especieService.editarEspecie(id,dto);

    }




}
