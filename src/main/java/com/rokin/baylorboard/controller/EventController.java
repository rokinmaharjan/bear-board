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

import com.rokin.baylorboard.domain.Event;
import com.rokin.baylorboard.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@PostMapping
	public Event addEvent(@RequestBody Event event) {
		return eventService.addEvent(event);
	}

	@GetMapping
	public Map<String, Object> getEvents(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "20") Integer size) {
		return eventService.findEventsWithPaging(page, size);
	}

	@GetMapping("/slide")
	public Map<String, Object> getEventsforslide(@RequestParam(defaultValue = "0") Integer page,
										 @RequestParam(defaultValue = "20") Integer size) {
		return eventService.findEventsWithPaging(page, size);
	}

	@PutMapping("/{id}")
	public Event updateEvent(@PathVariable String id, @RequestBody Event event) {
		return eventService.updateEvent(id, event);
	}

	@GetMapping("/{id}")
	public Event getEvent(@PathVariable String id) {
		return eventService.findById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable String id) {
		eventService.deleteEvent(id);
	}
}
