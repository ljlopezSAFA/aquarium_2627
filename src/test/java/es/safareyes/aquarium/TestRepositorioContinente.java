package es.safareyes.aquarium;


import es.safareyes.aquarium.modelos.Continente;
import es.safareyes.aquarium.repositorios.IContinenteRepository;
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


    @Test
    void consultarContinentes() {

        List<Continente> todos = repository.findAll();


    }
}



