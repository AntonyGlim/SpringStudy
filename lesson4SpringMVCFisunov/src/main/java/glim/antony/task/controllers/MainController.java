package glim.antony.task.controllers;

import glim.antony.task.entities.Product;
import glim.antony.task.repositories.ProductsRepository;
import glim.antony.task.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    private ProductsService productsService;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

//    @GetMapping("/employees")
//    public String showEmployeesPage(Model model) {
//        List<Employee> employeesList = employeesService.findAll();
//        model.addAttribute("employees", employeesList);
//        return "employees";
//    }

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        List<Product> productsList = productsService.findAll();
        for (Product product : productsList) {
            System.out.println(product);
        }
        model.addAttribute("products", productsList);
        return "products";
    }

//    @GetMapping("/submit_form")
//    @ResponseBody
//    public String getFormResult(@RequestParam(name = "word") String word, @RequestParam(name = "value") int value) {
//        return "Word: " + word + "; Value = " + value + ";";
//    }
//
//    @GetMapping("/test_custom_method")
//    @ResponseBody
//    public List<Employee> testCustomMethod() {
//        return employeesRepository.findAllByIdBetween(1L, 3L);
//    }
}
