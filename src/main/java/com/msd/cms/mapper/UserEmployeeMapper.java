package com.msd.cms.mapper;

import com.msd.cms.entities.Customer;
import com.msd.cms.entities.User;
import com.msd.cms.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserEmployeeMapper {


    @Autowired
    ModelMapper modelMapper;

    @Autowired
     RoleRepository roleService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public User mapCustomerToUser(Customer customer) {
        User user = new User();
        user.setName(customer.getName());
        user.setUsername(customer.getUsername());
        user.setPassword(this.bCryptPasswordEncoder.encode(customer.getPassword()));
        user.setAuthorities(new ArrayList<>());
        user.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));


        return user;
    }
}
