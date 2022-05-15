package com.rokin.baylorboard.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rokin.baylorboard.domain.Event;
import com.rokin.baylorboard.repository.EventRepository;
import com.rokin.baylorboard.utils.CustomBeanUtils;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository;

	public Event addEvent(Event event) {
		return eventRepository.save(event);
	}

	public Map<String, Object> findEventsWithPaging(Integer page, Integer size) {
		Page<Event> events = eventRepository.findAll(PageRequest.of(page, size));
		
		Map<String, Object> eventsMap = new HashMap<>();
		eventsMap.put("events", events.getContent());
		eventsMap.put("pages", events.getTotalPages());

		return eventsMap;
	}

	public Event updateEvent(String id, Event updatedEvent) {
		Event event = eventRepository.findById(id).get();

		CustomBeanUtils.copyNonNullProperties(updatedEvent, event);

		return eventRepository.save(event);
	}

	public Event findById(String id) {
		return eventRepository.findById(id).get();
	}

	public void deleteEvent(String id) {
		eventRepository.deleteById(id);
	}

}
