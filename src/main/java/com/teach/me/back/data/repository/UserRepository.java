package com.teach.me.back.data.repository;


import com.teach.me.back.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User queryById(Long id);

}
