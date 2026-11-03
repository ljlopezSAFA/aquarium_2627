package es.safareyes.aquarium.conversores;

import es.safareyes.aquarium.dtos.BiotopoDTO;
import es.safareyes.aquarium.modelos.Biotopo;
import org.mapstruct.Mapper;
import java.util.*;

@Mapper(componentModel = "spring")
public interface BiotopoMapper {

    BiotopoDTO toDTO(Biotopo entity);
    List<BiotopoDTO> toDTO(List<Biotopo> entity);



}
