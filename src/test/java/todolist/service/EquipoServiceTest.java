package todolist.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import todolist.dto.EquipoData;
import todolist.model.Equipo;
import todolist.repository.EquipoRepository;
import java.util.List;
import todolist.dto.UsuarioData;
import todolist.model.Usuario;
import todolist.repository.UsuarioRepository;
import static org.junit.jupiter.api.Assertions.assertThrows;



import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = "/clean-db.sql")
public class EquipoServiceTest {

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @Transactional
    public void crearEquipoServicio() {

        // WHEN
        EquipoData equipoData =
                equipoService.crearEquipo("Backend");

        // THEN
        assertThat(equipoData.getId()).isNotNull();

        Equipo equipoBD =
                equipoRepository.findById(equipoData.getId())
                        .orElse(null);

        assertThat(equipoBD).isNotNull();

        assertThat(equipoBD.getNombre())
                .isEqualTo("Backend");
    }

    @Test
    @Transactional
    public void recuperarListaEquiposOrdenada() {

        // GIVEN
        equipoService.crearEquipo("Frontend");

        equipoService.crearEquipo("Backend");

        equipoService.crearEquipo("DevOps");

        // WHEN
        List<EquipoData> equipos =
                equipoService.findAllOrdenadoPorNombre();

        // THEN
        assertThat(equipos).hasSize(3);

        assertThat(equipos.get(0).getNombre())
                .isEqualTo("Backend");

        assertThat(equipos.get(1).getNombre())
                .isEqualTo("DevOps");

        assertThat(equipos.get(2).getNombre())
                .isEqualTo("Frontend");
    }
    @Test
    @Transactional
    public void añadirUsuarioAEquipo() {

        // GIVEN
        EquipoData equipo =
                equipoService.crearEquipo("Backend");

        Usuario usuario =
                new Usuario("user@umh.es");

        usuarioRepository.save(usuario);

        // WHEN
        equipoService.añadirUsuarioAEquipo(
                equipo.getId(),
                usuario.getId()
        );

        // THEN
        List<UsuarioData> usuarios =
                equipoService.usuariosEquipo(
                        equipo.getId());

        assertThat(usuarios).hasSize(1);

        assertThat(usuarios.get(0).getEmail())
                .isEqualTo("user@umh.es");
    }

    @Test
    @Transactional
    public void recuperarEquiposUsuario() {

        // GIVEN
        EquipoData backend =
                equipoService.crearEquipo("Backend");

        EquipoData frontend =
                equipoService.crearEquipo("Frontend");

        Usuario usuario =
                new Usuario("user@umh.es");

        usuarioRepository.save(usuario);

        equipoService.añadirUsuarioAEquipo(
                backend.getId(),
                usuario.getId());

        equipoService.añadirUsuarioAEquipo(
                frontend.getId(),
                usuario.getId());

        // WHEN
        List<EquipoData> equipos =
                equipoService.equiposUsuario(
                        usuario.getId());

        // THEN
        assertThat(equipos).hasSize(2);

        assertThat(equipos.get(0).getNombre())
                .isEqualTo("Backend");

        assertThat(equipos.get(1).getNombre())
                .isEqualTo("Frontend");
    }
    @Test
    @Transactional
    public void crearEquipoDuplicadoLanzaExcepcion() {

        // GIVEN
        equipoService.crearEquipo("Backend");

        // THEN
        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.crearEquipo(
                        "Backend")
        );
    }
    
    @Test
    @Transactional
    public void eliminarUsuarioDeEquipo() {

        // GIVEN
        EquipoData equipo =
                equipoService.crearEquipo("Backend");

        Usuario usuario =
                new Usuario("user@umh.es");

        usuarioRepository.save(usuario);

        equipoService.añadirUsuarioAEquipo(
                equipo.getId(),
                usuario.getId()
        );

        List<UsuarioData> usuariosAntes =
                equipoService.usuariosEquipo(
                        equipo.getId());

        assertThat(usuariosAntes).hasSize(1);

        // WHEN
        equipoService.eliminarUsuarioDeEquipo(
                equipo.getId(),
                usuario.getId()
        );

        // THEN
        List<UsuarioData> usuariosDespues =
                equipoService.usuariosEquipo(
                        equipo.getId());

        assertThat(usuariosDespues).isEmpty();

        List<EquipoData> equiposUsuario =
                equipoService.equiposUsuario(
                        usuario.getId());

        assertThat(equiposUsuario).isEmpty();
    }
    @Test
    @Transactional
    public void eliminarUsuarioDeEquipoLanzaExcepcion() {

        // GIVEN
        EquipoData equipo =
                equipoService.crearEquipo("Backend");

        Usuario usuario =
                new Usuario("user@umh.es");

        usuarioRepository.save(usuario);

        // THEN
        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.eliminarUsuarioDeEquipo(
                        999L,
                        usuario.getId())
        );

        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.eliminarUsuarioDeEquipo(
                        equipo.getId(),
                        999L)
        );
    }
    @Test
    @Transactional
    public void añadirUsuarioDuplicadoAEquipoLanzaExcepcion() {

        // GIVEN
        EquipoData equipo =
                equipoService.crearEquipo("Backend");

        Usuario usuario =
                new Usuario("user@umh.es");

        usuarioRepository.save(usuario);

        equipoService.añadirUsuarioAEquipo(
                equipo.getId(),
                usuario.getId()
        );

        // THEN
        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.añadirUsuarioAEquipo(
                        equipo.getId(),
                        usuario.getId())
        );
    }
    @Test
    @Transactional
    public void añadirUsuarioAEquipoLanzaExcepcionSiDatosInvalidos() {

        // GIVEN
        EquipoData equipo =
                equipoService.crearEquipo("Backend");

        Usuario usuario =
                new Usuario("user@umh.es");

        usuarioRepository.save(usuario);

        // THEN
        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.añadirUsuarioAEquipo(
                        999L,
                        usuario.getId())
        );

        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.añadirUsuarioAEquipo(
                        equipo.getId(),
                        999L)
        );
    }
    @Test
    @Transactional
    public void listarMembresiasLanzaExcepcionSiDatosInvalidos() {

        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.usuariosEquipo(999L)
        );

        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.equiposUsuario(999L)
        );
    }
    @Test
    @Transactional
    public void crearEquipoConNombreVacioLanzaExcepcion() {

        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.crearEquipo("")
        );

        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.crearEquipo("   ")
        );

        assertThrows(
                EquipoServiceException.class,

                () -> equipoService.crearEquipo(null)
        );
    }
}