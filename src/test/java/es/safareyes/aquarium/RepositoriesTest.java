package es.safareyes.aquarium;

import es.safareyes.aquarium.modelos.Especie;
import es.safareyes.aquarium.repositorios.IEspecieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import java.util.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTest {


    @Autowired
    private IEspecieRepository especieRepository;


    @Test
    void encuentraLosPrestamosActivos() {
        List<Especie> activos = especieRepository.findAll();


    }
}
