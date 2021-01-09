package com.msd.cms.service;

import com.msd.cms.entities.User;
import com.msd.cms.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

	User findUserByUserName(String name);


	UserServiceModel editUserProfile(UserServiceModel userServiceModel, String oldPassword);

	List<User> findAllUsers();


	User findUserById(String id);

	void deleteUser(String    userId);

}
