package com.rokin.baylorboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rokin.baylorboard.domain.Award;
import com.rokin.baylorboard.service.AwardService;

@RestController
@RequestMapping("/awards")
public class AwardController {

	@Autowired
	private AwardService awardService;

	@PostMapping
	public Award addAward(@RequestBody Award award) {
		return awardService.add(award);
	}

	@GetMapping
	public Map<String, Object> getAwards(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "20") Integer size) {
		return awardService.findEventsWithPaging(page, size);
	}

	@PutMapping("/{id}")
	public Award updateAward(@PathVariable String id, @RequestBody Award award) {
		return awardService.update(id, award);
	}

	@GetMapping("/{id}")
	public Award getEvent(@PathVariable String id) {
		return awardService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable String id) {
		awardService.delete(id);
	}

}
