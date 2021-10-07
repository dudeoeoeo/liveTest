package com.rest.live.web;

import com.rest.live.domain.user.LoginResponseUser;
import com.rest.live.domain.user.MypageUserResponse;
import com.rest.live.domain.user.User;
import com.rest.live.domain.user.UserRepository;
import com.rest.live.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @PostMapping("/enroll")
    public User enroll(@RequestBody User user) {
        User newUser = userService.join(user);
        return newUser;
    }

    @PostMapping("/login")
    public LoginResponseUser login(@RequestBody User user) {
        User loginUser = userService.LoginUser(user);

        if(loginUser != null) {
            System.out.println("loginUser1: ");
            httpSession.setAttribute("id", loginUser.getId());
            httpSession.setAttribute("email", loginUser.getEmail());
            httpSession.setAttribute("name", loginUser.getName());
            httpSession.setAttribute("role", loginUser.getRole().getTitle());
            LoginResponseUser l_u = new LoginResponseUser(loginUser);
            return l_u;
        }
        System.out.println("null");
        return null;
    }

    @GetMapping("/signout")
    public String logout() {
        if(httpSession.getAttribute("email") != null) {
            httpSession.invalidate();
            return "로그아웃 되었습니다.";
        }
        return "로그인한 상태가 아닙니다.";
    }

    @PostMapping("/user/denied")
    public String denied() {
        System.out.println("user denied called");
        return "auth denied";
    }

    @GetMapping("mypage")
    public Object mypage() {
        if(httpSession.getAttribute("id") != null) {
            Long user_id = (Long) httpSession.getAttribute("id");
            MypageUserResponse mr = userService.findById(user_id);;
            return mr;
        }
        return "로그인 후 이용 가능한 서비스입니다.";
    }
}
