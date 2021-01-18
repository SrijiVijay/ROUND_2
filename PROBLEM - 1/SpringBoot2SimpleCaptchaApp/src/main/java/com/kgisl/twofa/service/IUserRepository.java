package com.kgisl.twofa.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kgisl.twofa.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

}
