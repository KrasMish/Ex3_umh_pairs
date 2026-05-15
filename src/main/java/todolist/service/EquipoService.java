package todolist.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todolist.dto.EquipoData;
import todolist.model.Equipo;
import todolist.repository.EquipoRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public EquipoData crearEquipo(String nombre) {

        Equipo equipo = new Equipo(nombre);

        equipoRepository.save(equipo);

        return modelMapper.map(equipo, EquipoData.class);
    }

    @Transactional(readOnly = true)
    public List<EquipoData> findAllOrdenadoPorNombre() {

        List<Equipo> equipos =
                equipoRepository.findAllByOrderByNombreAsc();

        return equipos.stream()
                .map(equipo ->
                        modelMapper.map(equipo, EquipoData.class))
                .collect(Collectors.toList());
    }
}