package com.example.demo;

import com.example.demo.domain.entities.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;



public interface DemoRepository extends JpaRepository<Todo, Integer>{
   
    
}
