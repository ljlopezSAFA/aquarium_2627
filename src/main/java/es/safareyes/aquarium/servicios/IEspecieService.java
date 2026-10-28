package es.safareyes.aquarium.servicios;

import es.safareyes.aquarium.dtos.EspecieDTO;

import java.util.List;
public interface IEspecieService {

    EspecieDTO guardarEspecie(EspecieDTO dto);

    List<EspecieDTO> consultarTodas();

}
