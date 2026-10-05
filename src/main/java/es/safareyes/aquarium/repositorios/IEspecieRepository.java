package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.dtos.InformeOrganismos;
import es.safareyes.aquarium.modelos.Especie;
import es.safareyes.aquarium.modelos.catalogos.Continente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface IEspecieRepository extends JpaRepository<Especie,Integer> {


    /**
     * JPQL Mix Java y SQL
     *
     * @param nombre
     * @return
     */
    @Query("select e from Especie e where e.tipoAgua.nombre = :nombreAgua ")
    List<Especie> buscarEspeciePorNombreTipoAgua(@Param("nombreAgua") String nombre);



    @Query(value = "select e.* from especie e \n" +
            "join cat_tipo_agua cta on cta.id = e.id_tipo_agua \n" +
            "where cta.nombre  = :agua ", nativeQuery = true)
    List<Continente> buscarPorAgua(@Param("agua") String nombreAgua);




    @Query(nativeQuery = true, value = "select cto.nombre as tipo_organismo , count(e.*) as recuento " +
            " from especie e\n" +
            "join cat_tipo_organismo cto on cto.id  =  e.id_tipo_organismo \n" +
            "group by cto.nombre")
    List<InformeOrganismos> numeroDeEspeciesPorOrganismo();










}
