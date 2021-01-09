package com.msd.cms.service;


import com.msd.cms.entities.Role;

import java.util.List;

public interface RoleService {

    void seedRolesInDb();

    List<Role> findAllRoles();

    Role findByAuthority(String authority);

    Role findById(String id);
}
