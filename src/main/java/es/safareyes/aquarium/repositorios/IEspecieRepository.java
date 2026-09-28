package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEspecieRepository extends JpaRepository<Especie,Integer> {
}
