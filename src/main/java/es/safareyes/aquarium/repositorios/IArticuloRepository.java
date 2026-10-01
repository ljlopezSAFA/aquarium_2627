package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArticuloRepository extends JpaRepository<Articulo,Integer> {
}
