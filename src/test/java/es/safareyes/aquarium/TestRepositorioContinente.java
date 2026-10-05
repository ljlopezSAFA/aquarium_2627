package es.safareyes.aquarium;


import es.safareyes.aquarium.dtos.EspeciesPorFamilia;
import es.safareyes.aquarium.dtos.InformeOrganismos;
import es.safareyes.aquarium.modelos.Especie;
import es.safareyes.aquarium.modelos.catalogos.Continente;
import es.safareyes.aquarium.repositorios.IContinenteRepository;
import es.safareyes.aquarium.repositorios.IEspecieRepository;
import es.safareyes.aquarium.repositorios.IFamiliaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestRepositorioContinente {

    @Autowired
    private IContinenteRepository repository;

    @Autowired
    private IEspecieRepository especieRepository;

    @Autowired
    private IFamiliaRepository familiaRepository;


    @Test
    void consultarContinentes() {

        List<Continente> todos = repository.findAllByNombreContainingAndOrdenIsLessThan("américa", 4);

    }



    @Test
    void consultarEspecies() {

        List<InformeOrganismos> todos = especieRepository.numeroDeEspeciesPorOrganismo();

    }



    @Test
    void consultarEspeciesPorFamilia() {

        List<EspeciesPorFamilia> todos = familiaRepository.buscarNumeroEspeciesPorFamilia();

    }
}



