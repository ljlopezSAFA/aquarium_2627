package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.Acuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAcuarioRepository extends JpaRepository<Acuario,Integer> {
}
