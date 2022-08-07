package dcc193.ufjf.br.josue.tarefa;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository rep;

    @RequestMapping({ "/", "/index.html" })
    @ResponseBody
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("usuario-index");
        mv.addObject("mensagem", "Olá mundo");
        return mv;
    }

    @RequestMapping(path = "/novo.html", method = RequestMethod.GET)
    public ModelAndView novaGet() {
        ModelAndView mv = new ModelAndView("usuario-new");
        Usuario t = new Usuario();
        System.out.println(t);
        mv.addObject(t);
        return mv;
    }

    @RequestMapping(path = "/novo.html", method = RequestMethod.POST)
    public ModelAndView novaPost(@Valid Usuario u, BindingResult binding) {
        ModelAndView mv = new ModelAndView("usuario-new");
        if (binding.hasErrors()) {
            mv.setViewName("usuario-new");
            mv.addObject("usuario", u);
            return mv;
        }
        rep.save(u);
        mv.setViewName("redirect:listar.html");
        return mv;
    }

    @RequestMapping(path = "/listar.html", method = RequestMethod.GET)
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("usuario-list");
        List<Usuario> tl = rep.findAll();
        mv.addObject("usuarios", tl);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarGET(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        Optional<Usuario> usuarioop = rep.findById(id);
        if (usuarioop.isPresent()) {
            Usuario u = usuarioop.get();
            mv.setViewName("usuario-edit");
            mv.addObject("usuario", u);
            return mv;
        }
        mv.setViewName("redirect:../listar.html");
        return mv;
    }

    @PostMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, @Valid Usuario usuario, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("usuario-edit");
            mv.addObject("usuario", usuario);
            return mv;
        }

        rep.save(usuario);
        mv.setViewName("redirect:../listar.html");
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView deletarGET(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:../listar.html");
        Optional<Usuario> usuarioop = rep.findById(id);
        if (usuarioop.isPresent()) {
            Usuario u = usuarioop.get();
            rep.delete(u);
        }
        return mv;
    }

}
