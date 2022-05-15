package com.rokin.baylorboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rokin.baylorboard.domain.WhitelistUser;
import com.rokin.baylorboard.repository.WhitelistUserRepository;

@Service
public class WhitelistUserService {

	@Autowired
	private WhitelistUserRepository whitelistUserRepository;

	public List<WhitelistUser> getWhitelistUsers() {
		return whitelistUserRepository.findAll();
	}

	public WhitelistUser add(WhitelistUser whitelistUser) {
		return whitelistUserRepository.save(whitelistUser);
	}

	public List<WhitelistUser> findAll() {
		return whitelistUserRepository.findAll();
	}

	public void delete(String id) {
		whitelistUserRepository.deleteById(id);
	}

}
