package com.cydeo.repository;

import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.service.RoleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {



}
