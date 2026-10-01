package es.safareyes.aquarium.repositorios;

import es.safareyes.aquarium.modelos.catalogos.Continente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface IContinenteRepository extends JpaRepository<Continente, Integer> {


    /**
     * Usando JPA Interfaz
     *
     *
     * @param nombre
     * @param orden
     * @return
     */
    List<Continente> findAllByNombreContainingAndOrdenIsLessThan(String nombre, Integer orden);












}
