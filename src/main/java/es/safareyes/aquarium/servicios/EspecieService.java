package es.safareyes.aquarium.servicios;

import es.safareyes.aquarium.conversores.EspecieMapper;
import es.safareyes.aquarium.dtos.EspecieDTO;
import es.safareyes.aquarium.repositorios.IEspecieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EspecieService {

    private IEspecieRepository especieRepository;
    private EspecieMapper especieMapper;


    public List<EspecieDTO> consultarTodas(){
        return especieMapper.toDto(especieRepository.findAll());
    }

}
