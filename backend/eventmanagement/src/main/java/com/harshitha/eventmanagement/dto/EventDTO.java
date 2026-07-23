package com.harshitha.eventmanagement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EventDTO {

	@NotBlank(message = "Title is required")
    @Size(max = 100)
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    @Size(max = 100)
    private String location;

    @Future(message = "Event date must be in the future")
    private LocalDate eventDate;

    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    public EventDTO() {
    }

    public EventDTO(String title, String description, String location,
                    LocalDate eventDate, Integer capacity) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.eventDate = eventDate;
        this.capacity = capacity;
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
}