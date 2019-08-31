package glim.antony;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Задача контроллера будет заключаться в передаче сообщения и возврате имени представления
 * index.jsp . Класс контроллера будет иметь следующий вид:
 */
@Controller
public class WelcomeController {
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("message", "Hello everybody!");
        return "index";
    }
}
