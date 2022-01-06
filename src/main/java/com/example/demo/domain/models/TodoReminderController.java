package com.example.demo.domain.models;

//import java.util.ArrayList;
import java.util.Collection;
//import java.util.Date;
import java.util.List;
import java.util.Optional;
//import java.util.Set;

//import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import com.example.demo.DemoRepository;
//import com.example.demo.domain.entities.Todo;
import com.example.demo.domain.entities.TodoReminder;

import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.HttpClientErrorException.BadRequest;

@RestController
@RequestMapping("/reminders")
public class TodoReminderController {
    
    
    private TodoReminderRepository myTodoRepo;
    private DemoRepository _todosRepository;

   // List<TodoReminder> addDemoReminder = null;

    public TodoReminderController(TodoReminderRepository myRemRepo, DemoRepository todoRepository){
        this.myTodoRepo = myRemRepo;
        this._todosRepository = todoRepository;
    }

    @GetMapping("")
    public ResponseEntity<?> getReminders(HttpServletRequest request) {

        Collection<TodoReminder> todoRemind = this.myTodoRepo.findAll();
        return ResponseEntity.ok(todoRemind);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getReminderForTodo(HttpServletRequest request,  @PathVariable int todoId) {

        Optional<TodoReminder> todoRemind = this.myTodoRepo.findById(todoId);
        return ResponseEntity.ok(todoRemind);
    }


    @PostMapping("/todos/{todoId}/add")
    public ResponseEntity<?> addReminderForTodo(HttpServletRequest request, @RequestBody TodoReminder Reminderpayload, @PathVariable int todoId) {
        // Collection<TodoReminder> allTodos = this.myTodoRepo.findAll();
        boolean todoExist = this._todosRepository.existsById(todoId);
        if(!todoExist)
            return ResponseEntity.badRequest().body("No todo found with Id"+todoId);
        this.myTodoRepo.save(Reminderpayload);
        
        TodoReminder todoReminder = new TodoReminder();
        todoReminder.setId(todoId);      
        Example<TodoReminder> criteria = Example.of(todoReminder);
        List<TodoReminder> allRemindersForTodo = myTodoRepo.findAll(criteria);
        return ResponseEntity.ok(allRemindersForTodo);
    }

    @PutMapping("/todos/{todoId}/Update")
    public ResponseEntity<?> updateReminderForTodo(HttpServletRequest request, @RequestBody TodoReminder updateReminderpayload, @PathVariable int todoId) {
      Optional<TodoReminder> updateTodoRemind = this.myTodoRepo.findById(todoId);
     // boolean todoExists = this._todosRepository.existsById(todoId);
      if(updateTodoRemind.isPresent()){
        TodoReminder myTodoReminder = updateTodoRemind.get();
        myTodoReminder.setLabel(updateReminderpayload.getLabel());
        myTodoReminder.setDueDate(updateReminderpayload.getDueDate());
        this.myTodoRepo.save(myTodoReminder);
      }else{
        return ResponseEntity.badRequest().body("No todo found with entered Id "+todoId+ " Update failed");
      }
      TodoReminder todoReminder = new TodoReminder();
        todoReminder.setId(todoId);      
        Example<TodoReminder> criteria = Example.of(todoReminder);
        List<TodoReminder> UpdatedRemindersForTodo = myTodoRepo.findAll(criteria);
        return ResponseEntity.ok(UpdatedRemindersForTodo);
    }  

    @DeleteMapping("/deletetodoReminder/{id}")
  public ResponseEntity<HttpStatus> deleteTodoReminderbyId(@PathVariable int todoId) {
    try {
        myTodoRepo.deleteById(todoId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  

    @DeleteMapping("/deleteTodosReminders")
  public ResponseEntity<HttpStatus> deleteAllTodosReminders() {
    try {
      myTodoRepo.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }	
  }
    /* private List<TodoReminder> loadReminders(){
        List<TodoReminder> addDemoReminder = new ArrayList<TodoReminder>();

        addDemoReminder.add(new TodoReminder(1, "Setup Java Data and JPA", new Date()));
        addDemoReminder.add(new TodoReminder(2, "Check if Setup for Java Data and JPA is working", new Date()));
         return addDemoReminder;     
    } 
    
    @PutMapping("/todos/{todoId}/Update")
    public ResponseEntity<?> addReminderForTodo(HttpServletRequest request, @RequestBody TodoReminder updateReminderpayload, @PathVariable int todoId) {
      Optional<TodoReminder> updateTodoRemind = this.myTodoRepo.findById(todoId);
      if(updateTodoRemind.isPresent()){
        TodoReminder myTodoReminder = updateTodoRemind.get();
        myTodoReminder.setlabel(updateReminderpayload.getlabel());
        myTodoReminder.setdue_date(updateReminderpayload.getdue_date());
        
      }else{
        return ResponseEntity.badRequest().body("No todo found with entered Id"+todoId+ "Update failed");
        this.myTodoRepo.save(updateReminderpayload);
      }
    
    */
}
