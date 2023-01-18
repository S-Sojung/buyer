package shop.mtcoding.buyer.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer.model.Product;
import shop.mtcoding.buyer.model.ProductRepository;
import shop.mtcoding.buyer.model.PurchaseRepository;
import shop.mtcoding.buyer.model.User;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping({ "/", "/product" })
    public String home(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product/list";
    }

    @GetMapping("/product/{id}")
    public String detail(Model model, @PathVariable int id) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product/detail";
    }

    @PostMapping("/product/{id}/buy")
    public String buy(@PathVariable int id, int count) {
        Product product = productRepository.findById(id);
        int qtyNum = product.getQty() - count;
        int result = productRepository.updateByQty(id, qtyNum);

        User user = (User) session.getAttribute("principal");
        int result2 = purchaseRepository.insert(user.getId(), id);

        if (result == 1 && result2 == 1) {
            return "redirect:/product/";
        } else {
            return "redirect:/product/detail" + id;
        }
    }
}
