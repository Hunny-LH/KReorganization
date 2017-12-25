package com.github.lh.controller;

import com.github.lh.authentication.JwtFilter;
import com.github.lh.authentication.UpcToken;
import com.github.lh.common.JwtUtils;
import com.github.lh.dao.UserRepo;
import com.github.lh.domain.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-25
 */
@Controller
public class DefaultController {

    @Autowired
    UserRepo userRepo;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> login(UpcToken token) {
        Map<String, String> res = new HashMap<>(1);
        try {
            // 使用用户名密码登录
            SecurityUtils.getSubject().login(token);
            // 登录成功后返回jwt
            res.put(JwtFilter.DEFAULT_JWT_PARAM, buildJwt());
        } catch (Exception e) {
            res.put("err", e.getMessage());
        }
        return res;
    }

    private String buildJwt() throws Exception {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return JwtUtils.createJWT("app", user.getUsername(), 3600);
    }

    @GetMapping("/logout")
    @ResponseBody
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "ok";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> selectUsers() {
        return userRepo.findAll();
    }
}
