package glim.antony.controllers;

import glim.antony.entities.Product;
import glim.antony.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    // http://localhost:8189/app/products/processForm
    @RequestMapping("processForm")
    public String processForm(@ModelAttribute("product") Product product){
        productsService.add(product);
        return "product-form";
    }

    // http://localhost:8189/app/products/showAll
    @RequestMapping("showAll")
    public String showAll(Model model){
        List<Product> products = productsService.getProductList();
        model.addAttribute("products", products);
        return "product-show-all";
    }

}
