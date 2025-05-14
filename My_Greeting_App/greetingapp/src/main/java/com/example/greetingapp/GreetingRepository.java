package com.example.greetingapp;

import com.example.greetingapp.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface GreetingRepository extends JpaRepository<Greeting, Long>{

}
