package glim.antony.task.controllers;

import glim.antony.task.entities.Product;
import glim.antony.task.repositories.ProductsRepository;
import glim.antony.task.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        List<Product> productsList = productsService.findAll();
        model.addAttribute("products", productsList);
        return "products";
    }

    @GetMapping("/minmaxfilter")
    @ResponseBody
    public String showProductsWithFilterByMinMaxCost(
            @RequestParam(name = "minPrice") int minPrice,
            @RequestParam(name = "maxPrice") int maxPrice
    ){
        return minPrice + " " + maxPrice;
//        return "redirect:/products/";
    }

    @GetMapping("/submit_form")
    @ResponseBody
    public String getFormResult(@RequestParam(name = "minOrMax") String word, Model model) {
        List<Product> productsList = new ArrayList<>();
        if (word.equalsIgnoreCase("min")){
            productsList.add((Product)productsService.findByCost(12));
        }
        if (word.equalsIgnoreCase("max")){

        }
        if (word.equalsIgnoreCase("minandmax")){

        }
        model.addAttribute("products", productsList);
        return "products";
    }
}
