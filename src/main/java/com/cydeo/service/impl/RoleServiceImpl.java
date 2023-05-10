package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    @Override
    public List<RoleDTO> listAllRoles() {
        return null;
    }

    @Override
    public RoleDTO findById(Long is) {
        return null;
    }
}

/***
 * It appears that you are using this code to create a Java class that implements the RoleService interface.
 *
 * In Java, an interface is a collection of abstract methods that define a contract for a group of
 * related classes to implement. By implementing an interface, a class agrees to provide concrete
 * implementations of all the methods defined in the interface.
 *
 * The RoleService interface likely defines a set of methods that are related to managing user roles
 * in a system. By creating a class that implements this interface, you are providing concrete
 * implementations of these methods and defining how the role-related functionality will work in your system.
 *
 * The RoleServiceImpl class is the actual implementation of the RoleService interface. It contains the
 * code for each of the methods defined in the interface, and may also include additional methods or
 * properties that are specific to the implementation. By implementing this class, you can use it to
 * manage user roles in your system.
 */