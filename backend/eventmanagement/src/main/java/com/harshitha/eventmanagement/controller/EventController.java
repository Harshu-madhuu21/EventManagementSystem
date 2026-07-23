package com.harshitha.eventmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.harshitha.eventmanagement.dto.EventDTO;
import com.harshitha.eventmanagement.entity.Event;
import com.harshitha.eventmanagement.service.EventService;
import java.util.List;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody EventDTO eventDTO) {
        return new ResponseEntity<>(eventService.createEvent(eventDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {

        List<Event> events = eventService.getAllEvents();

        return ResponseEntity.ok(events);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {

        Event event = eventService.getEventById(id);

        return ResponseEntity.ok(event);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventDTO eventDTO) {

        return ResponseEntity.ok(eventService.updateEvent(id, eventDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {

        eventService.deleteEvent(id);

        return ResponseEntity.ok("Event deleted successfully.");
    }
 // Search events by location
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Event>> getEventsByLocation(@PathVariable String location) {
        return ResponseEntity.ok(eventService.getEventsByLocation(location));
    }

    // Search events by date
    @GetMapping("/date/{eventDate}")
    public ResponseEntity<List<Event>> getEventsByDate(@PathVariable LocalDate eventDate) {
        return ResponseEntity.ok(eventService.getEventsByDate(eventDate));
    }

    // Search events by title
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Event>> getEventsByTitle(@PathVariable String title) {
        return ResponseEntity.ok(eventService.getEventsByTitle(title));
    }
    @GetMapping("/page")
    public ResponseEntity<Page<Event>> getAllEventsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "eventId") String sortBy) {

        return ResponseEntity.ok(eventService.getAllEvents(page, size, sortBy));
    }
}