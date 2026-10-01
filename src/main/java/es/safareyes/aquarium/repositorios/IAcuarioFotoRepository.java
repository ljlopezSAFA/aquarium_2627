package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.AcuarioFoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAcuarioFotoRepository extends JpaRepository<AcuarioFoto,Integer> {
}
