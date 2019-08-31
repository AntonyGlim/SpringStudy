package glim.antony.controllers;

import glim.antony.entities.Product;
import glim.antony.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    // http://localhost:8189/app/products/showForm
    @RequestMapping("/showForm")
    public String showForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-form";
    }
}
