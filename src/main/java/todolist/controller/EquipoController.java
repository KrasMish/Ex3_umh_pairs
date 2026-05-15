package todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import todolist.authentication.ManagerUserSession;
import todolist.dto.EquipoData;
import todolist.dto.UsuarioData;
import todolist.service.EquipoService;
import todolist.service.UsuarioService;

import java.util.List;

@Controller
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ManagerUserSession managerUserSession;

    @GetMapping("/equipos")
    public String listadoEquipos(Model model) {

        Long usuarioId = managerUserSession.usuarioLogeado();

        UsuarioData usuario =
                usuarioService.findById(usuarioId);

        List<EquipoData> equipos =
                equipoService.findAllOrdenadoPorNombre();

        List<EquipoData> equiposUsuario =
                equipoService.equiposUsuario(usuarioId);

        model.addAttribute("usuario", usuario);
        model.addAttribute("equipos", equipos);
        model.addAttribute("equiposUsuario", equiposUsuario);

        return "listaEquipos";
    }
}