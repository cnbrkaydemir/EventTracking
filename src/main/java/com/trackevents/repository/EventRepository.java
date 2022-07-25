package com.trackevents.repository;

import com.trackevents.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Events,Integer> {

    Events findByEventId(int id);

}
