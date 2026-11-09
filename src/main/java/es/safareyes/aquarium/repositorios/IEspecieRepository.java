package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.dtos.InformeOrganismos;
import es.safareyes.aquarium.modelos.Especie;
import es.safareyes.aquarium.modelos.catalogos.Continente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface IEspecieRepository extends JpaRepository<Especie,Integer> {



    @Query("""
                SELECT e FROM Especie e
                    JOIN FETCH e.familia
                    JOIN FETCH e.dificultad
                    join fetch  e.biotopos
            """)
    List<Especie> buscarConDetalleFamilia();



    @EntityGraph(attributePaths = { "familia", "dificultad" })
    List<Especie> findAll();




    /**
     * JPQL Mix Java y SQL
     *
     * @param nombre
     * @return
     */
    @Query("select e from Especie e where e.tipoAgua.nombre = :nombreAgua ")
    List<Especie> buscarEspeciePorNombreTipoAgua(@Param("nombreAgua") String nombre);



    @Query(
            "select e from Especie  e join " +
                    "fetch e.dificultad " +
                    "join fetch e.familia " +
                    "where " +
                    "(:nombre is null or e.nombreComun  = :nombre)" +
                    "and " +
                    "(:familia is null or e.familia.nombre  = :familia)" +
                    "and"+
                    "(:dificultad is null or e.dificultad.nombre  = :dificultad) "
    )
    List<Especie> buscarConFiltros(@Param("nombre") String nombre,
                                   @Param("familia") String familia,
                                   @Param("dificultad") String dificultad);





    @Query(value = "select e.* from especie e \n" +
            "join cat_tipo_agua cta on cta.id = e.id_tipo_agua \n" +
            "where cta.nombre  = :agua ", nativeQuery = true)
    List<Continente> buscarPorAgua(@Param("agua") String nombreAgua);




    @Query(nativeQuery = true, value = "select cto.nombre as tipo_organismo , count(e.*) as recuento " +
            " from especie e\n" +
            "join cat_tipo_organismo cto on cto.id  =  e.id_tipo_organismo \n" +
            "group by cto.nombre")
    List<InformeOrganismos> numeroDeEspeciesPorOrganismo();


    boolean existsByNombreCientifico(@NotBlank(message = "El nombre científico no puede estar vacío") @Size(max = 150, message = "El nombre científico no debe superar los 150 caracteres") String s);

    boolean existsBySlug(@NotBlank(message = "El slug no puede estar vacío") @Size(max = 160, message = "El slug no debe superar los 160 caracteres") String slug);



    @Query("select e from Especie e" +
            " join fetch  e.tipoOrganismo where " +
            " e.id = :id")
    Optional<Especie> buscarPorIdConDatosTipoOrganismo(@Param("id") Integer id);






}
