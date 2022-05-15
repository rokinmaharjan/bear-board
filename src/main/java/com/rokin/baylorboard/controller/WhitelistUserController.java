package com.rokin.baylorboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rokin.baylorboard.domain.WhitelistUser;
import com.rokin.baylorboard.service.WhitelistUserService;


@RestController
@RequestMapping("/whitelist-users")
public class WhitelistUserController {
	@Autowired
	private WhitelistUserService whitelistUserService;

	@PostMapping
	public WhitelistUser addWhitelistUser(@RequestBody WhitelistUser whitelistUser) {
		return whitelistUserService.add(whitelistUser);
	}

	@GetMapping
	public List<WhitelistUser> getAllWhitelistUsers() {
		return whitelistUserService.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void deleteWhitelistUser(@PathVariable String id) {
		whitelistUserService.delete(id);
	}

}
