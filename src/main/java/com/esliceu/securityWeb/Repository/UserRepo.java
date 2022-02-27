package com.esliceu.securityWeb.Repository;

import com.esliceu.securityWeb.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {



    User findByEmail(String userName);
}