package es.safareyes.aquarium.servicios;

import es.safareyes.aquarium.conversores.EspecieMapper;
import es.safareyes.aquarium.dtos.CrearEspeciePezDTO;
import es.safareyes.aquarium.dtos.EditarEspeciePezDTO;
import es.safareyes.aquarium.dtos.EspecieDTO;
import es.safareyes.aquarium.excepciones.ElementoNoEncontrado;
import es.safareyes.aquarium.excepciones.ReglaNegocioException;
import es.safareyes.aquarium.excepciones.RelacionNoEncontrada;
import es.safareyes.aquarium.modelos.Biotopo;
import es.safareyes.aquarium.modelos.Especie;
import es.safareyes.aquarium.modelos.EspeciePez;
import es.safareyes.aquarium.modelos.Familia;
import es.safareyes.aquarium.modelos.catalogos.TipoOrganismo;
import es.safareyes.aquarium.repositorios.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EspecieService implements IEspecieService {

    private IEspecieRepository especieRepository;
    private EspecieMapper especieMapper;
    private IEspeciePezRepository especiePezRepository;

    // Repositorios de Relaciones y Catálogos
    private ITipoOrganismoRepository tipoOrganismoRepository;
    private IFamiliaRepository familiaRepository;
    private ITipoAguaRepository tipoAguaRepository;
    private IDificultadRepository dificultadRepository;
    private IUsuarioRepository usuarioRepository;
    private IBiotopoRepository biotopoRepository;

    // Repositorios específicos de EspeciePez
    private IDietaRepository dietaRepository;
    private ITemperamentoRepository temperamentoRepository;
    private INivelNadoRepository nivelNadoRepository;
    private ITipoReproduccionRepository tipoReproduccionRepository;
    private IEstadoConservacionRepository estadoConservacionRepository;


    @Override
    public EspecieDTO guardarEspecie(EspecieDTO dto) {
        return null;
    }

    public List<EspecieDTO> consultarTodas() {
        return especieMapper.toDto(especieRepository.buscarConDetalleFamilia());
    }


    public List<EspecieDTO> filtrar(String nombre,
                                    String familia,
                                    String dificultad) {
        return especieMapper.toDto(especieRepository
                .buscarConFiltros(nombre, familia, dificultad));
    }


    public void crearEspeciePez(CrearEspeciePezDTO dto) {

        // 1. Validaciones de negocio y unicidad
        validarUnicidad(dto);
        validarRangosMinMax(dto);

        // 2. Mapear DTO a entidad EspeciePez
        EspeciePez pez = especieMapper.toEntity(dto);

        // 3. Validar y asignar relaciones OBLIGATORIAS (Especie)
        pez.setTipoOrganismo(tipoOrganismoRepository.findById(dto.idTipoOrganismo())
                .orElseThrow(() -> new RelacionNoEncontrada("El tipo de organismo indicado no existe")));

        // En BD: DEFAULT 1 (DULCE). Se asigna idTipoAgua o por defecto 1 si viene nulo
        Short idTipoAgua = dto.idTipoAgua() != null ? dto.idTipoAgua() : 1;
        pez.setTipoAgua(tipoAguaRepository.findById(idTipoAgua)
                .orElseThrow(() -> new RelacionNoEncontrada("El tipo de agua indicado no existe")));

        pez.setDificultad(dificultadRepository.findById(dto.idDificultad())
                .orElseThrow(() -> new RelacionNoEncontrada("La dificultad indicada no existe")));

        // 4. Validar y asignar relaciones OBLIGATORIAS (EspeciePez)
        pez.setDieta(dietaRepository.findById(dto.idDieta())
                .orElseThrow(() -> new RelacionNoEncontrada("La dieta indicada no existe")));

        pez.setTemperamento(temperamentoRepository.findById(dto.idTemperamento())
                .orElseThrow(() -> new RelacionNoEncontrada("El temperamento indicado no existe")));

        pez.setNivelNado(nivelNadoRepository.findById(dto.idNivelNado())
                .orElseThrow(() -> new RelacionNoEncontrada("El nivel de nado indicado no existe")));

        // 5. Validar y asignar relaciones OPCIONALES (Especie)
        if (dto.idFamilia() != null) {
            pez.setFamilia(familiaRepository.findById(dto.idFamilia())
                    .orElseThrow(() -> new RelacionNoEncontrada("La familia indicada no existe")));
        }

        if (dto.idCreadoPor() != null) {
            pez.setCreadoPor(usuarioRepository.findById(dto.idCreadoPor())
                    .orElseThrow(() -> new RelacionNoEncontrada("El usuario creador no existe")));
        }

        if (dto.idsBiotopos() != null && !dto.idsBiotopos().isEmpty()) {
            List<Biotopo> biotopos = biotopoRepository.findAllById(dto.idsBiotopos());
            if (biotopos.size() != dto.idsBiotopos().size()) {
                throw new RelacionNoEncontrada("Uno o varios biotopos indicados no existen");
            }
            pez.setBiotopos(biotopos);
        }

        // 6. Validar y asignar relaciones OPCIONALES (EspeciePez)
        if (dto.idTipoReproduccion() != null) {
            pez.setTipoReproduccion(tipoReproduccionRepository.findById(dto.idTipoReproduccion())
                    .orElseThrow(() -> new RelacionNoEncontrada("El tipo de reproducción indicado no existe")));
        }

        if (dto.idDificultadReproduccion() != null) {
            pez.setDificultadReproduccion(dificultadRepository.findById(dto.idDificultadReproduccion())
                    .orElseThrow(() -> new RelacionNoEncontrada("La dificultad de reproducción indicada no existe")));
        }

        if (dto.idEstadoConservacion() != null) {
            pez.setEstadoConservacion(estadoConservacionRepository.findById(dto.idEstadoConservacion())
                    .orElseThrow(() -> new RelacionNoEncontrada("El estado de conservación indicado no existe")));
        }

        // 7. Persistir en base de datos
        especieRepository.save(pez);
    }


    private void validarUnicidad(CrearEspeciePezDTO dto) {
        if (especieRepository.existsByNombreCientifico(dto.nombreCientifico())) {
            throw new ReglaNegocioException("Ya existe una especie registrada con el nombre científico: " + dto.nombreCientifico());
        }
        if (especieRepository.existsBySlug(dto.slug())) {
            throw new ReglaNegocioException("Ya existe una especie registrada con el slug: " + dto.slug());
        }
    }

    private void validarRangosMinMax(CrearEspeciePezDTO dto) {
        validarRango(dto.temperaturaMin(), dto.temperaturaMax(), "temperatura");
        validarRango(dto.phMin(), dto.phMax(), "pH");
        validarRango(dto.ghMin(), dto.ghMax(), "gH");
        validarRango(dto.khMin(), dto.khMax(), "kH");
    }

    private void validarRango(BigDecimal min, BigDecimal max, String campo) {
        if (min != null && max != null && min.compareTo(max) > 0) {
            throw new ReglaNegocioException(String.format("El %s mínimo (%s) no puede ser mayor que el %s máximo (%s)", campo, min, campo, max));
        }
    }


    public void eliminar(Integer id) {

        //Validación
        Especie especie = especieRepository.buscarPorIdConDatosTipoOrganismo(id)
                .orElseThrow(() -> new ElementoNoEncontrado("No se ha encontrado especie con ese id"));

        TipoOrganismo tipoOrganismoPez = tipoOrganismoRepository.findFirstByCodigo("PEZ");

        if (especie != null && !especie.getTipoOrganismo().equals(tipoOrganismoPez)) {
            especieRepository.deleteById(id);
        } else {
            throw new ReglaNegocioException("No se puede eliminar una especie del tipo PEZ");
        }
    }


    public void cambiarEstadoEspecie(Integer id) {
        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> new ElementoNoEncontrado("No se ha encontrado especie con ese ID"));

        if (especie.getPublicada() != false) {
            especie.setPublicada(false);
            especieRepository.save(especie);

        } else {
            throw new ReglaNegocioException("No se puede deshabilitar una especie que ya está desahabilitada");
        }


    }


    public void editarEspecie(Integer id, EditarEspeciePezDTO dto) {

        EspeciePez pez = especiePezRepository.findById(id)
                .orElseThrow(() -> new ElementoNoEncontrado("No se encuentra Especie con ese id"));


        //Volcar los datos del dto en la entity (Ya existe)
        especieMapper.actualizar(pez, dto);


        // 3. Validar y asignar relaciones OBLIGATORIAS (Especie)
        if (dto.idTipoOrganismo() != null) {
            pez.setTipoOrganismo(tipoOrganismoRepository.findById(dto.idTipoOrganismo())
                    .orElseThrow(() -> new RelacionNoEncontrada("El tipo de organismo indicado no existe")));
        }


        // En BD: DEFAULT 1 (DULCE). Se asigna idTipoAgua o por defecto 1 si viene nulo
        if (dto.idTipoAgua() != null) {
            Short idTipoAgua = dto.idTipoAgua() != null ? dto.idTipoAgua() : 1;
            pez.setTipoAgua(tipoAguaRepository.findById(idTipoAgua)
                    .orElseThrow(() -> new RelacionNoEncontrada("El tipo de agua indicado no existe")));
        }

        if(dto.idDificultad()!=null){
            pez.setDificultad(dificultadRepository.findById(dto.idDificultad())
                    .orElseThrow(() -> new RelacionNoEncontrada("La dificultad indicada no existe")));

        }

        // 4. Validar y asignar relaciones OBLIGATORIAS (EspeciePez)
        if(dto.idDieta()!=null){
            pez.setDieta(dietaRepository.findById(dto.idDieta())
                    .orElseThrow(() -> new RelacionNoEncontrada("La dieta indicada no existe")));
        }

        if(dto.idTemperamento()!=null){
            pez.setTemperamento(temperamentoRepository.findById(dto.idTemperamento())
                    .orElseThrow(() -> new RelacionNoEncontrada("El temperamento indicado no existe")));
        }


        if(dto.idNivelNado()!=null){
            pez.setNivelNado(nivelNadoRepository.findById(dto.idNivelNado())
                    .orElseThrow(() -> new RelacionNoEncontrada("El nivel de nado indicado no existe")));
        }


        // 5. Validar y asignar relaciones OPCIONALES (Especie)
        if (dto.idFamilia() != null) {
            pez.setFamilia(familiaRepository.findById(dto.idFamilia())
                    .orElseThrow(() -> new RelacionNoEncontrada("La familia indicada no existe")));
        }

        if (dto.idCreadoPor() != null) {
            pez.setCreadoPor(usuarioRepository.findById(dto.idCreadoPor())
                    .orElseThrow(() -> new RelacionNoEncontrada("El usuario creador no existe")));
        }

        if (dto.idsBiotopos() != null && !dto.idsBiotopos().isEmpty()) {
            List<Biotopo> biotopos = biotopoRepository.findAllById(dto.idsBiotopos());
            if (biotopos.size() != dto.idsBiotopos().size()) {
                throw new RelacionNoEncontrada("Uno o varios biotopos indicados no existen");
            }
            pez.setBiotopos(biotopos);
        }

        // 6. Validar y asignar relaciones OPCIONALES (EspeciePez)
        if (dto.idTipoReproduccion() != null) {
            pez.setTipoReproduccion(tipoReproduccionRepository.findById(dto.idTipoReproduccion())
                    .orElseThrow(() -> new RelacionNoEncontrada("El tipo de reproducción indicado no existe")));
        }

        if (dto.idDificultadReproduccion() != null) {
            pez.setDificultadReproduccion(dificultadRepository.findById(dto.idDificultadReproduccion())
                    .orElseThrow(() -> new RelacionNoEncontrada("La dificultad de reproducción indicada no existe")));
        }

        if (dto.idEstadoConservacion() != null) {
            pez.setEstadoConservacion(estadoConservacionRepository.findById(dto.idEstadoConservacion())
                    .orElseThrow(() -> new RelacionNoEncontrada("El estado de conservación indicado no existe")));
        }


        // 7. Persistir en base de datos
        especieRepository.save(pez);


    }


}
