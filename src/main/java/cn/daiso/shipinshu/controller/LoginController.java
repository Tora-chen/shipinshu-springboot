package cn.daiso.shipinshu.controller;

import cn.daiso.shipinshu.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

//        这是一次push测试
//        测试git冲突

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication); // 保存认证信息到 SecurityContext

            String token = JwtUtil.generateToken(authentication.getName());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            // 添加用户身份信息
            response.put("role", authentication.getAuthorities().iterator().next().getAuthority());
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "登录失败: 用户名或密码错误");
            return ResponseEntity.status(401).body(response);
        }
    }
}

