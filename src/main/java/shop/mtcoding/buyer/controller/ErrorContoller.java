package shop.mtcoding.buyer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorContoller {

    @GetMapping("/notfound")
    public String error() {
        return "error/error";
    }
}
