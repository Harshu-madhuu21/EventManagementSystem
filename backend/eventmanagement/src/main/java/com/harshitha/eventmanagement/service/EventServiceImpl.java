package com.harshitha.eventmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.harshitha.eventmanagement.dto.EventDTO;
import com.harshitha.eventmanagement.entity.Event;
import com.harshitha.eventmanagement.repository.EventRepository;
import java.util.List;
import com.harshitha.eventmanagement.exception.ResourceNotFoundException;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;
    
    
    @Override
    public Event createEvent(EventDTO eventDTO) {

        Event event = new Event();

        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setEventDate(eventDTO.getEventDate());
        event.setCapacity(eventDTO.getCapacity());

        return eventRepository.save(event);
    }
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    @Override
    public Event getEventById(Long id) {

        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + id));
    }
    @Override
    public Event updateEvent(Long id, EventDTO eventDTO) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + id));

        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setLocation(eventDTO.getLocation());
        event.setEventDate(eventDTO.getEventDate());
        event.setCapacity(eventDTO.getCapacity());

        return eventRepository.save(event);
    }
    @Override
    public void deleteEvent(Long id) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + id));

        eventRepository.delete(event);
    }
    @Override
    public List<Event> getEventsByLocation(String location) {
        return eventRepository.findByLocation(location);
    }

    @Override
    public List<Event> getEventsByDate(LocalDate eventDate) {
        return eventRepository.findByEventDate(eventDate);
    }

    @Override
    public List<Event> getEventsByTitle(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title);
    }
    @Override
    public Page<Event> getAllEvents(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return eventRepository.findAll(pageable);
    }
}