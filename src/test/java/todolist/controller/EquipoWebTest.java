package todolist.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import todolist.authentication.ManagerUserSession;
import todolist.dto.EquipoData;
import todolist.dto.UsuarioData;
import todolist.service.EquipoService;
import todolist.service.UsuarioService;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/clean-db.sql")
public class EquipoWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private ManagerUserSession managerUserSession;

    private UsuarioData addUsuarioBD() {

        UsuarioData usuario = new UsuarioData();
        usuario.setEmail("user@umh.es");
        usuario.setPassword("1234");

        return usuarioService.registrar(usuario);
    }

    @Test
    public void listaEquiposMuestraEquiposDisponibles() throws Exception {

        // GIVEN
        UsuarioData usuario = addUsuarioBD();

        EquipoData backend =
                equipoService.crearEquipo("Backend");

        EquipoData frontend =
                equipoService.crearEquipo("Frontend");

        equipoService.añadirUsuarioAEquipo(
                backend.getId(),
                usuario.getId());

        when(managerUserSession.usuarioLogeado())
                .thenReturn(usuario.getId());

        // WHEN, THEN
        this.mockMvc.perform(get("/equipos"))
                .andExpect(content().string(allOf(
                        containsString("Teams"),
                        containsString("Backend"),
                        containsString("Frontend"),
                        containsString("Leave"),
                        containsString("Join")
                )));
    }
    @Test
    public void postNuevoEquipoCreaEquipoYRedirigeAListaEquipos() throws Exception {

        // GIVEN
        UsuarioData usuario = addUsuarioBD();

        when(managerUserSession.usuarioLogeado())
                .thenReturn(usuario.getId());

        // WHEN, THEN
        this.mockMvc.perform(post("/equipos/nuevo")
                        .param("nombre", "DevOps"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/equipos"));

        this.mockMvc.perform(get("/equipos"))
                .andExpect(content().string(containsString("DevOps")));
    }
}