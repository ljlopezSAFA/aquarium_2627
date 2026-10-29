package es.safareyes.aquarium.servicios;

import es.safareyes.aquarium.conversores.EspecieMapper;
import es.safareyes.aquarium.dtos.EspecieDTO;
import es.safareyes.aquarium.repositorios.IEspecieRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EspecieService implements IEspecieService {

    private IEspecieRepository especieRepository;
    private EspecieMapper especieMapper;


    @Override
    public EspecieDTO guardarEspecie(EspecieDTO dto) {
        return null;
    }

    public List<EspecieDTO> consultarTodas(){
        return especieMapper.toDto(especieRepository.buscarConDetalleFamilia());
    }


    public List<EspecieDTO> filtrar(String nombre,
                                    String familia,
                                    String dificultad){
        return especieMapper.toDto(especieRepository
                .buscarConFiltros(nombre, familia, dificultad));

    }


}
