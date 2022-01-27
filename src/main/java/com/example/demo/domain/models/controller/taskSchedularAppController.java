package com.example.demo.domain.models.controller;

//import java.util.ArrayList;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.taskSchedulerRepository;
import com.example.demo.domain.entities.Todo;

//import org.hibernate.criterion.Example;
//import org.hibernate.criterion.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController
@RequestMapping("/todos")
public class taskSchedularAppController {

	//@Autowired
	private taskSchedulerRepository _myDemoRepository;
   // private List<Todo> _todos = null;
	
	public taskSchedularAppController(taskSchedulerRepository demoRepo) {
		this._myDemoRepository = demoRepo;

	}
	
    
     @PutMapping("/{todoId}/Update")
    public ResponseEntity<?> updateReminderForTodo(HttpServletRequest request, @RequestBody Todo updateTodopayload, @PathVariable int todoId) {
      java.util.Optional<Todo> updateTodo = this._myDemoRepository.findById(todoId);
    //  boolean todoExists = this._myDemoRepository.existsById(todoId);
      if(updateTodo.isPresent()){
        Todo myTodoUpdate = updateTodo.get();
        myTodoUpdate.setTitle(updateTodopayload.getTitle());
        myTodoUpdate.setDescription(updateTodopayload.getDescription());
        myTodoUpdate.setCreatedDate(updateTodopayload.getCreatedDate());
        this._myDemoRepository.save(myTodoUpdate);
      }else{
        return ResponseEntity.badRequest().body("No todo found with entered Id "+todoId+ " Update failed");
      }
      //Todo todoUdate = new Todo();
       // todoUdate.setId(todoId);      
        //Example<Todo> criteria = Example.of(todoUdate);
       // List<Todo> UpdatedTodo = _myDemoRepository.findAll(criteria);
       List<Todo> UpdatedTodo = this._myDemoRepository.findAll();

        return ResponseEntity.ok(UpdatedTodo);
    }
    

    @GetMapping("")
    public ResponseEntity<?> getTodos(HttpServletRequest request) {

        List<Todo> todos = this._myDemoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addTodo(HttpServletRequest request, @RequestBody Todo payload) {
      this._myDemoRepository.save(payload);// this._todos.add(payload);
      List<Todo> allTodos = this._myDemoRepository.findAll();

        return ResponseEntity.ok(allTodos);
    }

      
    
    @DeleteMapping("/deletetodo/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable int todoId) {
      try {
          _myDemoRepository.deleteById(todoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }  
  
      @DeleteMapping("/deleteTodos")
    public ResponseEntity<HttpStatus> deleteAllTodos() {
      try {
        _myDemoRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }	
    }

  /*  private List<Todo> loadMockTodos(){
        List<Todo> todos = new ArrayList<Todo>();

        todos.add(new Todo(1, "Setup Java Data and JPA", "Setup Java Data and JPA ", new Date()));
        todos.add(new Todo(1, "Setup Unit & integration testing", "Setup Unit & integration testing ", new Date()));
        
        return todos;
    } 
    
        @PutMapping("/todos/{todoId}/Update")
    public ResponseEntity<?> updateTodo(HttpServletRequest request, @RequestBody Todo updateTodopayload, @PathVariable int todoId) {
     // Optional<Todo> updateTodoRemind = this.myTodoRepo.findById(todoId);
      boolean todoExists = this._todosRepository.existsById(todoId);
      if(todoExists){
        Todo myTodoReminder = updateTodoRemind.findAll();
        myTodoReminder.setLabel(updateTodopayload.getLabel());
        myTodoReminder.setDueDate(updateTodopayload.getDueDate());
        this.myTodoRepo.save(updateTodopayload);
      }else{
        return ResponseEntity.badRequest().body("No todo found with entered Id "+todoId+ " Update failed");
      }
      Todo todoReminder = new TodoReminder();
        todoReminder.setId(todoId);      
        Example<Todo> criteria = Example.of(todoReminder);
        List<Todo> updateTodo = myTodoRepo.findAll(criteria); 
               return ResponseEntity.ok(updateTodo);
    }  

    @DeleteMapping("/deletetodo/{id}")
  public ResponseEntity<HttpStatus> deleteTodo(@PathVariable int todoId) {
    try {
        _myDemoRepository.deleteById(todoId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

    @DeleteMapping("/deleteTodos")
  public ResponseEntity<HttpStatus> deleteAllTodos() {
    try {
      _myDemoRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }	
  }
    
    */
}
