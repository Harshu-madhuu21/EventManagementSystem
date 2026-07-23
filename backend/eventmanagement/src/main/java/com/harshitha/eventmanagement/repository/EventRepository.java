package com.harshitha.eventmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harshitha.eventmanagement.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByLocation(String location);
    List<Event> findByEventDate(LocalDate eventDate);
    List<Event> findByTitleContainingIgnoreCase(String title);
}  