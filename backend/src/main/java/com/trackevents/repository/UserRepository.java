package com.trackevents.repository;

import com.trackevents.model.Events;
import com.trackevents.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByUserId(int id);

    Optional<Users> findByUserEmail(String email);
}
