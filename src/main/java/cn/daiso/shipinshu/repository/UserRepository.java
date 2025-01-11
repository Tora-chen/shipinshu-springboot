package cn.daiso.shipinshu.repository;

import cn.daiso.shipinshu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getUserByUsername(String username);
}

