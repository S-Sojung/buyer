package shop.mtcoding.buyer.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import shop.mtcoding.buyer.dto.PurchaseAllDto;
import shop.mtcoding.buyer.model.Product;
import shop.mtcoding.buyer.model.ProductRepository;
import shop.mtcoding.buyer.model.Purchase;
import shop.mtcoding.buyer.model.PurchaseRepository;
import shop.mtcoding.buyer.model.User;
import shop.mtcoding.buyer.service.PurchaseService;

//
@Controller
public class PurchaseController {

    @Autowired
    private HttpSession session;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseService purchaseService;

    /*
     * 목적 : 세션이 있는지 체크, 구매 히스토리 남기기(insert), 재고 수량 변경
     */
    // @Transactional // 트랜잭션 관리해줌 다른 스레드가 변경 요청을 할 수 없도록 rock 걸림
    @PostMapping("/purchase/insert")
    public String insert(int productId, int count) {
        // 1. 세션이 있는지 체크 (컨트롤러 역할)
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/notfound";
        }
        // 2. 서비스 호출
        int result = purchaseService.구매하기(principal.getId(), productId, count);
        if (result == -1) {
            return "redirect:/notfound";
        }

        return "redirect:/";
    }

    @PostMapping("/purchase/{id}/delete")
    public String delete(@PathVariable int id) {
        /// purchase/${purchase.id}/delete

        int result = purchaseService.구매취소하기(id);
        if (result == -1) {
            return "redirect:/notfound";
        }

        return "redirect:/purchase";
    }

    @GetMapping("/purchase")
    public String purchase(Model model) {
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/notfound";
        }

        List<PurchaseAllDto> purchaseList = purchaseRepository.findByUserId(principal.getId());

        model.addAttribute("purchaseList", purchaseList);
        return "purchase/list";
    }
}
