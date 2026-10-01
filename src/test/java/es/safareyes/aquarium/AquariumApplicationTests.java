package es.safareyes.aquarium;

import es.safareyes.aquarium.modelos.Biotopo;
import es.safareyes.aquarium.modelos.Especie;
import es.safareyes.aquarium.modelos.catalogos.Continente;
import es.safareyes.aquarium.repositorios.IBiotopoRepository;
import es.safareyes.aquarium.repositorios.IContinenteRepository;
import es.safareyes.aquarium.repositorios.IEspecieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AquariumApplicationTests {


    @Autowired
    private IContinenteRepository repository;

    @Autowired
    private IEspecieRepository especieRepository;

    @Autowired
    private IBiotopoRepository biotopoRepository;


    @Test
    void encuentraLosPrestamosActivos() {
        List<Especie> activos = especieRepository.findAll();


    }


    @Test
    void buscaBiotopos() {
        List<Biotopo> activos = biotopoRepository.buscarPorIdContinenteYPorPalabraDescripcion(
                1, "fondo");


    }
    @Test
    void consultarContinentes() {

        List<Continente> todos = repository.findAllByNombreContainingAndOrdenIsLessThan("américa", 4);

    }



    @Test
    void consultarEspecies() {

        List<Especie> todos = especieRepository.buscarEspeciePorNombreTipoAgua("Agua dulce");

    }

}
