package com.prob_jr.sikcal_app.domain.repository;


import com.prob_jr.sikcal_app.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByName(String name);

}
