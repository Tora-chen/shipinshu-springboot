package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> getRolesById(Long id);
}
