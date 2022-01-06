package com.example.demo.domain.models;

import com.example.demo.domain.entities.TodoReminder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoReminderRepository extends JpaRepository<TodoReminder, Integer>{
    
}
