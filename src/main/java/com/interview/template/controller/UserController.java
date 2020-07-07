package com.interview.template.controller;

import com.interview.template.exceptions.ReservedUserNameException;
import com.interview.template.exceptions.UserAlreadyExitException;
import java.util.List;

import com.interview.template.exceptions.UserNotFoundException;
import com.interview.template.model.UserEntity;
import com.interview.template.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.BASE_URL)
@AllArgsConstructor
class UserController {

	static final String BASE_URL = "/api/v1/users";

	private final UserService userService;
        

	@GetMapping
	public List<UserEntity> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	UserEntity getUser(@PathVariable Long userId) throws UserNotFoundException {
		return userService.getUser(userId);
	}

	@RequestMapping(method = RequestMethod.HEAD, value = "/{userId}")
	void checkExists(@PathVariable Long userId) throws UserNotFoundException {
		userService.checkUserExists(userId);
	}
        
        @PostMapping("/user")
	public UserEntity createUser(@RequestBody UserEntity userEntity) throws UserAlreadyExitException, ReservedUserNameException {
		return userService.createUser(userEntity);
	}

	@DeleteMapping(value ="/user/{id}")
	void deleteUser(@PathVariable Long id) throws UserNotFoundException{
		userService.deleteUser(id);
	}

	@GetMapping("/searchusername/{username}")
	public List<UserEntity> searchUserByName(@PathVariable String username) {
		return userService.searchUserByName(username);
	}
}
