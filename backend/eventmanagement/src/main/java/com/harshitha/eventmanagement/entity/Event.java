package com.harshitha.eventmanagement.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;


    @Column(nullable = false, length = 100)
    private String title;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;


    @Column(nullable = false, length = 100)
    private String location;


    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    private Integer capacity;

    // Default Constructor
    public Event() {
    }

    // Parameterized Constructor
    public Event(Long eventId, String title, String description, String location,
                 LocalDate eventDate, Integer capacity) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.capacity = capacity;
    }

    // Getters and Setters

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Event [eventId=" + eventId + ", title=" + title + ", description=" + description
                + ", location=" + location + ", eventDate=" + eventDate
                + ", capacity=" + capacity + "]";
    }
}
