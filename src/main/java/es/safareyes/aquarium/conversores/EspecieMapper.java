package es.safareyes.aquarium.conversores;


import es.safareyes.aquarium.dtos.EspecieDTO;
import es.safareyes.aquarium.modelos.Especie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.*;

@Mapper(componentModel = "spring", uses = {FamiliaMapper.class})
public interface EspecieMapper {


    @Mapping(source = "dificultad.nombre", target = "dificultad")
    EspecieDTO toDto(Especie especie);


    List<EspecieDTO> toDto(List<Especie> especie);

}
