package cn.daiso.shipinshu.service;

import cn.daiso.shipinshu.entity.Role;
import cn.daiso.shipinshu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Loading user details for username: " + username);

        Optional<User> userOptional = userService.getByUsername(username);
        if (userOptional.isEmpty()) {
            System.out.println("User not found with username: " + username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        User user = userOptional.get();
        System.out.println("Found user: id=" + user.getId() + ", username=" + user.getUsername());

        // 直接获取用户的角色列表
        List<Role> roles = userRoleService.getRolesByUserId(user.getId());
        System.out.println("Found " + roles.size() + " roles for user " + user.getUsername());
        roles.forEach(role -> System.out.println("Role: id=" + role.getId() + ", name=" + role.getName()));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles.size());

        // 转换角色
        List<SimpleGrantedAuthority> roleAuthorities = roles.stream()
                .map(role -> {
                    String roleName = role.getName().startsWith("ROLE_") ?
                            role.getName() : "ROLE_" + role.getName();
                    System.out.println("Converting role: " + role.getName() + " -> " + roleName);
                    return roleName;
                })
                .map(SimpleGrantedAuthority::new)
                .toList();


        grantedAuthorities.addAll(roleAuthorities);
        System.out.println("Total authorities granted to user " + user.getUsername() + ": " + grantedAuthorities.size());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(grantedAuthorities)
                .build();
    }
}
