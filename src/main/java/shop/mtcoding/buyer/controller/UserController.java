package shop.mtcoding.buyer.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer.model.Purchase;
import shop.mtcoding.buyer.model.PurchaseRepository;
import shop.mtcoding.buyer.model.User;
import shop.mtcoding.buyer.model.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession session;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/logout")
    public String logout() {
        session.invalidate(); // 세션 아이디가 초기화된다.
        return "redirect:/";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/join")
    public String join(String username, String password, String email) {
        int result = userRepository.insert(username, password, email);
        if (result == 1) {
            return "redirect:/loginForm";
        } else {
            return "redirect:/joinForm";
        }
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String login(String username, String password, String remember, HttpServletResponse response) {
        // 서버가 클라이언트의 상태를 저장 : 왔던 사람인지 알 수 있는 것
        // 데이터베이스 입장에서 로그인은 select 지만 클라이언트 입장에서는 insert
        // 무엇을 수행하는지 확실하게 메서드 이름으로 적어주는게 좋다.
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return "redirect:/loginForm";
        } else { // principal 인증 : loginUser 라고도 함.
            if (remember == null) {
                remember = "";
            }
            // 로그인 됐을때,... 응답에 저장해야함.
            // 요청헤더 : Cookie
            // 응답헤더 : Set-Cookie
            if (remember.equals("on")) {
                Cookie cookie = new Cookie("remember", user.getUsername());
                response.addCookie(cookie);

            } else {
                Cookie cookie = new Cookie("remember", "");
                cookie.setMaxAge(0);// 빈 값을 덮어 씌우고, 쿠키의 시간을 0으로 설정해서 날려버리자
                response.addCookie(cookie);
            }

            session.setAttribute("principal", user);
            return "redirect:/";
        }

    }

}
