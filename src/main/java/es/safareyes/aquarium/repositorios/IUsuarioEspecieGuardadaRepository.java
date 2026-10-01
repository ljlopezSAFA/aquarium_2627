package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.UsuarioEspecieGuardada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioEspecieGuardadaRepository extends JpaRepository<UsuarioEspecieGuardada,Integer> {
}
