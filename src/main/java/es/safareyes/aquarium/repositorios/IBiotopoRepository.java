package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.Biotopo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface IBiotopoRepository extends JpaRepository<Biotopo,Short> {

    /**
     * Método de consulta por JPA Inteface
     *
     * @param nombre
     * @return
     */
    List<Biotopo> findAllByNombreEquals(String nombre);

    /**
     * JPQL
     *
     * @param nombreTipoAgua
     * @return
     */
    @Query("select b from Biotopo b where b.tipoAgua.nombre = :nombre")
    List<Biotopo> buscarPorTipoAgua(@Param("nombre") String nombreTipoAgua);


    @Query(value = "select * from biotopo b where id_continente = :id " +
            "and descripcion like concat('%', :descripcion, '%')",
            nativeQuery = true)
    List<Biotopo> buscarPorIdContinenteYPorPalabraDescripcion(@Param("id") Integer idContinente,
                                                              @Param("descripcion")String descripcion);















}
