package todolist.controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import todolist.authentication.ManagerUserSession;
import todolist.dto.EquipoData;
import todolist.dto.UsuarioData;
import todolist.service.EquipoService;
import todolist.service.UsuarioService;
import todolist.service.EquipoServiceException;
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
        @PostMapping("/equipos/nuevo")
    public String nuevoEquipo(@RequestParam String nombre,
                              RedirectAttributes flash) {

        equipoService.crearEquipo(nombre);

        flash.addFlashAttribute(
                "mensaje",
                "Equipo creado correctamente");

        return "redirect:/equipos";
    }
    @PostMapping("/equipos/{id}/join")
    public String joinEquipo(@PathVariable Long id,
                             RedirectAttributes flash) {

        Long usuarioId = managerUserSession.usuarioLogeado();

        equipoService.añadirUsuarioAEquipo(
                id,
                usuarioId);

        flash.addFlashAttribute(
                "mensaje",
                "Te has unido al equipo correctamente");

        return "redirect:/equipos";
    }
    @PostMapping("/equipos/{id}/leave")
    public String leaveEquipo(@PathVariable Long id,
                              RedirectAttributes flash) {

        Long usuarioId = managerUserSession.usuarioLogeado();

        equipoService.eliminarUsuarioDeEquipo(
                id,
                usuarioId);

        flash.addFlashAttribute(
                "mensaje",
                "Has salido del equipo correctamente");

        return "redirect:/equipos";
    }
        @GetMapping("/equipos/{id}")
        public String detalleEquipo(@PathVariable Long id, Model model) {
        Long usuarioId = managerUserSession.usuarioLogeado();

        UsuarioData usuario = usuarioService.findById(usuarioId);
        List<UsuarioData> usuariosEquipo = equipoService.usuariosEquipo(id);

        model.addAttribute("usuario", usuario);
        model.addAttribute("usuariosEquipo", usuariosEquipo);

        return "detalleEquipo";
        }

        @GetMapping("/equipos/{id}/editar")
public String editarEquipoForm(@PathVariable Long id, Model model) {

    Long usuarioId = managerUserSession.usuarioLogeado();

    UsuarioData usuario = usuarioService.findById(usuarioId);
    EquipoData equipo = equipoService.findById(id);

    model.addAttribute("usuario", usuario);
    model.addAttribute("equipo", equipo);

    return "formEditarEquipo";
}

@PostMapping("/equipos/{id}/editar")
public String editarEquipoSubmit(@PathVariable Long id,
                                 @RequestParam String nombre,
                                 RedirectAttributes flash) {

    try {
        equipoService.renombrarEquipo(id, nombre);
        flash.addFlashAttribute("mensaje", "Equipo renombrado correctamente");
    } catch (EquipoServiceException e) {
        flash.addFlashAttribute("error", e.getMessage());
    }

    return "redirect:/equipos";
}

@PostMapping("/equipos/{id}/eliminar")
public String eliminarEquipo(@PathVariable Long id,
                             RedirectAttributes flash) {

    try {
        equipoService.eliminarEquipo(id);
        flash.addFlashAttribute("mensaje", "Equipo eliminado correctamente");
    } catch (EquipoServiceException e) {
        flash.addFlashAttribute("error", e.getMessage());
    }

    return "redirect:/equipos";
}
}