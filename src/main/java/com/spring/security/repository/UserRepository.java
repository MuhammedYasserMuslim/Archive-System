package com.spring.security.repository;

import com.spring.security.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Byte> {
    /**
     * @return User by username
     * select * from users where name = :name
     */
    Optional<AppUser> findByUsername(String userName);

}
