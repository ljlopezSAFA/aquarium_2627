package es.safareyes.aquarium.servicios;


import es.safareyes.aquarium.modelos.catalogos.Continente;
import es.safareyes.aquarium.repositorios.IContinenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@AllArgsConstructor
public class ContinenteService {

    private IContinenteRepository repository;

    public List<Continente> consultarTodosContinetes() {
        return repository.findAll();
    }

}
