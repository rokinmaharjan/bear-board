package com.rokin.baylorboard.controller;

import com.rokin.baylorboard.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rokin.baylorboard.domain.User;
import com.rokin.baylorboard.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;


	@PostMapping
	public User addUsers(@RequestBody User user) {
		return userService.addUsers(user);
	}

	@PostMapping("/login")
	public User login(@Valid @RequestBody User user) {
		return userService.loginUser(user);
	}

	@GetMapping(produces="application/json")
	public List<User> allUsers() {
		return userService.allUsers();
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable String id, @RequestBody User user) {
		return userService.updateUser(id, user);
	}

	@GetMapping("/{id}")
	public User getUser(@PathVariable String id) {
		return userService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
	}

}
