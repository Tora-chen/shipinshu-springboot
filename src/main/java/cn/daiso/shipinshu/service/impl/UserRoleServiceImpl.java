package cn.daiso.shipinshu.service.impl;

import cn.daiso.shipinshu.entity.UserRole;
import cn.daiso.shipinshu.repository.RoleRepository;
import cn.daiso.shipinshu.repository.UserRoleRepository;
import cn.daiso.shipinshu.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import cn.daiso.shipinshu.entity.Role;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserRole> getUserRoles(@NonNull Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    @Override
    public boolean addUserRole(@NonNull Long userId, @NonNull Long roleId) {
        try {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            Instant now = Instant.now();
            userRoleRepository.save(userRole);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        System.out.println("Getting roles for userId: " + userId);
        // 1. 获取用户角色关联
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        if (userRoles.isEmpty()) {
            return Collections.emptyList();
        }

        // 2. 批量获取角色
        List<Long> roleIds = userRoles.stream()
                .map(UserRole::getRoleId)
                .toList();
        System.out.println("Found " + roleIds.size() + " roleIds for userId: " + userId);

        return roleRepository.findAllById(roleIds);
    }
}