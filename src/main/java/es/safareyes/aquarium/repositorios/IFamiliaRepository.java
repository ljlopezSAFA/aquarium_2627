package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.dtos.EspeciesPorFamilia;
import es.safareyes.aquarium.modelos.Especie;
import es.safareyes.aquarium.modelos.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface IFamiliaRepository extends JpaRepository<Familia,Integer> {

    Boolean existsByNombreIgnoreCase(String nombre);


    @Query(nativeQuery = true, value = "select f.nombre as familia, count(e.*) as especies  from especie e \n" +
            "join familia f on e.id_familia  = f.id \n" +
            "group by f.nombre")
    List<EspeciesPorFamilia> buscarNumeroEspeciesPorFamilia();


    @Query(nativeQuery = true, value = "select * from familia f  where \n" +
            "( :nombre  is null or nombre = :nombre )\n" +
            "and \n" +
            "( :orden is null or orden  = :orden )")
    List<Familia> filtrarFamilias(String nombre, String orden);




    @Query("select f from Familia f" +
            " join fetch f.tipoOrganismo" +
            " where f.id = :id")
    Optional<Familia> buscarFamiliaConTipoOrganismo(@Param("id") Integer id);


    List<Familia> findAllByActivaTrue();









}
