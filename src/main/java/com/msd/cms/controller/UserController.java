package com.msd.cms.controller;

import com.msd.cms.entities.Shipment;
import com.msd.cms.entities.User;
import com.msd.cms.models.binding.UserEditBindingModel;
import com.msd.cms.models.view.UserProfileViewModel;
import com.msd.cms.models.service.UserServiceModel;
import com.msd.cms.service.ShipmentService;
import com.msd.cms.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/users")
public class UserController  {

	private final UserService userService;
	private final ShipmentService shipmentService;
	private final ModelMapper modelMapper;

	@Autowired
	public UserController(UserService userService, ShipmentService shipmentService, ModelMapper modelMapper) {
		this.userService = userService;
		this.shipmentService = shipmentService;
		this.modelMapper = modelMapper;
	}



	@GetMapping("/login")
	@PreAuthorize("isAnonymous()")
	public String login() {
		return "login";
	}


	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String allUsers(Model model) {

		model.addAttribute("users", userService.findAllUsers());
		return "user/users-all";
	}

	@GetMapping("/profile")
	@PreAuthorize("isAuthenticated()")
	public String profile(Principal principal, Model model) {
		model.addAttribute("model", this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), User.class));
		return "user/profile";
	}

	@GetMapping("/edit")
	@PreAuthorize("isAuthenticated()")
	public String editProfile(Principal principal, Model model) {
		model.addAttribute("model", this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), UserProfileViewModel.class));
		return "user/edit-profile";
	}

	@PostMapping("/edit")
	@PreAuthorize("isAuthenticated()")
	public String editProfileConfirm(@ModelAttribute UserEditBindingModel model) {
		if(!model.getPassword().equals(model.getConfirmPassword())) {
			return "user/edit-profile";
		}
		this.userService.editUserProfile(this.modelMapper.map(model, UserServiceModel.class), model.getOldPassword());

		return "home";
	}

	@GetMapping("/deleteUser/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public String deleteUser(@PathVariable String id, Model model) {
		this.userService.deleteUser(id);
		model.addAttribute("users", userService.findAllUsers());
		return "user/users-all";
	}



}
