package dcc193.ufjf.br.josue.tarefa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/error.html")
    public String error() throws Exception {
        return "layout-padrao";
    }
}
