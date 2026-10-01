package es.safareyes.aquarium.repositorios;


import es.safareyes.aquarium.modelos.RegistroMantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegistroMantenimientoRepository extends JpaRepository<RegistroMantenimiento,Integer> {
}
