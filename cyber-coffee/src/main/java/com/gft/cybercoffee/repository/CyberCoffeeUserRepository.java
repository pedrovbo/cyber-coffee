package com.gft.cybercoffee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.cybercoffee.model.CyberCoffeeUser;

@Repository
public interface CyberCoffeeUserRepository extends JpaRepository<CyberCoffeeUser, Long>{    
    CyberCoffeeUser findByUsername(String username);
}
