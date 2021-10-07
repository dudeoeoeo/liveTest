package com.rest.live.web;

import com.rest.live.domain.user.User;
import com.rest.live.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AdminApiController {

    private final UserService userService;
    private final HttpSession httpSession;

    @PostMapping("/enroll/admin")
    public User enroll(@RequestBody Map<String, Object> map) {
        if(!map.get("admin").equals("admin"))
            return null;

        User newAdmin = userService.join(map);
        return newAdmin;
    }
    @GetMapping("/admin/userList")
    public Object getUserList() {
        String role = (String) httpSession.getAttribute("role");
        if(role == null || !role.equals("관리자"))
            return "권한이 없습니다.";
        return userService.getUserList();
    }
}
