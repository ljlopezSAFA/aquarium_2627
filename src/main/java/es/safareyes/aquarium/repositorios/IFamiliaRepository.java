package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.dtos.EspeciesPorFamilia;
import es.safareyes.aquarium.modelos.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface IFamiliaRepository extends JpaRepository<Familia,Integer> {



    @Query(nativeQuery = true, value = "select f.nombre as familia, count(e.*) as especies  from especie e \n" +
            "join familia f on e.id_familia  = f.id \n" +
            "group by f.nombre")
    List<EspeciesPorFamilia> buscarNumeroEspeciesPorFamilia();



}
