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
import todolist.dto.UsuarioData;
import todolist.model.Usuario;
import todolist.repository.UsuarioRepository;



@Service
public class EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public EquipoData crearEquipo(String nombre) {

        Equipo equipoExistente =
                equipoRepository.findByNombre(nombre);

        if (equipoExistente != null) {

            throw new EquipoServiceException(
                    "El equipo ya existe");
        }

        Equipo equipo = new Equipo(nombre);

        equipoRepository.save(equipo);

        return modelMapper.map(
                equipo,
                EquipoData.class);
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

    @Transactional
    public void añadirUsuarioAEquipo(Long equipoId,
                                     Long usuarioId) {

        Equipo equipo =
                equipoRepository.findById(equipoId)
                        .orElseThrow(() ->
                                new EquipoServiceException(
                                        "No existe equipo"));

        Usuario usuario =
                usuarioRepository.findById(usuarioId)
                        .orElseThrow(() ->
                                new EquipoServiceException(
                                        "No existe usuario"));

        if (equipo.getUsuarios().contains(usuario)) {
            throw new EquipoServiceException(
                    "El usuario ya pertenece al equipo");
        }

        equipo.addUsuario(usuario);
    }
    @Transactional(readOnly = true)
    public List<UsuarioData> usuariosEquipo(Long equipoId) {

        Equipo equipo =
                equipoRepository.findById(equipoId)
                        .orElseThrow(RuntimeException::new);

        return equipo.getUsuarios()
                .stream()
                .map(usuario ->
                        modelMapper.map(
                                usuario,
                                UsuarioData.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EquipoData> equiposUsuario(Long usuarioId) {

        Usuario usuario =
                usuarioRepository.findById(usuarioId)
                        .orElseThrow(RuntimeException::new);

        return usuario.getEquipos()
                .stream()
                .map(equipo ->
                        modelMapper.map(
                                equipo,
                                EquipoData.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public void eliminarUsuarioDeEquipo(Long equipoId,
                                        Long usuarioId) {

        Equipo equipo =
                equipoRepository.findById(equipoId)
                        .orElseThrow(() ->
                                new EquipoServiceException(
                                        "No existe equipo"));

        Usuario usuario =
                usuarioRepository.findById(usuarioId)
                        .orElseThrow(() ->
                                new EquipoServiceException(
                                        "No existe usuario"));

        equipo.removeUsuario(usuario);
    }
}