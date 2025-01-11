package cn.daiso.shipinshu.util;

import cn.daiso.shipinshu.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    // 获取当前用户的id
    public static Long getCurrentUserId(UserRepository userRepository) {
        // 从spring security获取用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        // 查找用户id
        return userRepository.getUserByUsername(username).get(0).getId();
    }
}
