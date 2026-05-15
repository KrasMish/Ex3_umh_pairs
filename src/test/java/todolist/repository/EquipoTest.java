package todolist.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import todolist.model.Equipo;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Sql(scripts = "/clean-db.sql")
public class EquipoTest {

    @Autowired
    private EquipoRepository equipoRepository;

    @Test
    public void crearEquipo() {

        Equipo equipo = new Equipo("Project P1");

        assertThat(equipo.getNombre()).isEqualTo("Project P1");
    }
    @Test
    @Transactional
    public void grabarYBuscarEquipo() {

        // GIVEN
        Equipo equipo = new Equipo("Project P1");

        // Проверка пустого конструктора для JPA
        Equipo equipo1 = new Equipo();

        // WHEN
        equipoRepository.save(equipo);

        // THEN
        Long equipoId = equipo.getId();

        assertThat(equipoId).isNotNull();

        Equipo equipoBD = equipoRepository.findById(equipoId).orElse(null);

        assertThat(equipoBD).isNotNull();

        assertThat(equipoBD.getNombre()).isEqualTo("Project P1");
    }
}