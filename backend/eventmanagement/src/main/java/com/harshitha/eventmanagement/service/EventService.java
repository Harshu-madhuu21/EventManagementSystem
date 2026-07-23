package com.harshitha.eventmanagement.service;

import com.harshitha.eventmanagement.dto.EventDTO;
import com.harshitha.eventmanagement.entity.Event;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
public interface EventService {

    Event createEvent(EventDTO eventDTO);
    List<Event> getAllEvents();
    Event getEventById(Long id);
    Event updateEvent(Long id, EventDTO eventDTO);
    void deleteEvent(Long id);
    List<Event> getEventsByLocation(String location);
    List<Event> getEventsByDate(LocalDate eventDate);
    List<Event> getEventsByTitle(String title);
    Page<Event> getAllEvents(int page, int size, String sortBy);
}