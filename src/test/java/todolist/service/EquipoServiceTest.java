package todolist.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import todolist.dto.EquipoData;
import todolist.model.Equipo;
import todolist.repository.EquipoRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = "/clean-db.sql")
public class EquipoServiceTest {

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private EquipoRepository equipoRepository;

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
}