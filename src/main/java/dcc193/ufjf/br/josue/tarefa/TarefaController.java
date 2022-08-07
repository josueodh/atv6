package dcc193.ufjf.br.josue.tarefa;

import java.util.Date;
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
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaRepository rep;

    @RequestMapping({ "/", "/index.html" })
    @ResponseBody
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("tarefa-index");
        mv.addObject("mensagem", "Olá mundo");
        return mv;
    }

    @RequestMapping(path = "/nova.html", method = RequestMethod.GET)
    public ModelAndView novaGet() {
        ModelAndView mv = new ModelAndView("tarefa-new");
        Tarefa t = new Tarefa("Criado em " + new Date());
        System.out.println(t);
        mv.addObject(t);
        return mv;
    }

    @RequestMapping(path = "/nova.html", method = RequestMethod.POST)
    public ModelAndView novaPost(@Valid Tarefa t, BindingResult binding) {
        ModelAndView mv = new ModelAndView("tarefa-new");
        if (binding.hasErrors()) {
            mv.setViewName("tarefa-new");
            mv.addObject("tarefa", t);
            return mv;
        }
        rep.save(t);
        mv.setViewName("redirect:listar.html");
        return mv;
    }

    @RequestMapping(path = "/listar.html", method = RequestMethod.GET)
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("tarefa-list");
        List<Tarefa> tl = rep.findAll();
        mv.addObject("tarefas", tl);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarGET(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        Optional<Tarefa> tarefaop = rep.findById(id);
        if (tarefaop.isPresent()) {
            Tarefa t = tarefaop.get();
            mv.setViewName("tarefa-edit");
            mv.addObject("tarefa", t);
            return mv;
        }
        mv.setViewName("redirect:../listar.html");
        return mv;
    }

    @PostMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable Long id, @Valid Tarefa tarefa, BindingResult binding) {
        ModelAndView mv = new ModelAndView();
        if (binding.hasErrors()) {
            mv.setViewName("tarefa-edit");
            mv.addObject("tarefa", tarefa);
            return mv;
        }

        rep.save(tarefa);
        mv.setViewName("redirect:../listar.html");
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView deletarGET(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:../listar.html");
        Optional<Tarefa> tarefaop = rep.findById(id);
        if (tarefaop.isPresent()) {
            Tarefa t = tarefaop.get();
            rep.delete(t);
        }
        return mv;
    }

}
