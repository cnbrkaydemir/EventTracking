package com.trackevents.repository;

import com.trackevents.model.Events;
import com.trackevents.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByUserId(int id);

    List<Users> findByEvents(Events events);



    List<Users> findByUserEmail(String email);
}
