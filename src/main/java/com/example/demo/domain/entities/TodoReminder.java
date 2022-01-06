package com.example.demo.domain.entities;

import java.util.Date;
//import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity(name = "todo_reminder")
public class TodoReminder {
	
	//public static final int Id = null;

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "RId")
    private int id;
	
    //public final static int Id;

    @Column(name = "todo_id")
    @JsonProperty("todoId")
    private int todo_id;

	@Column
    @JsonProperty("label")
    private String label;
	
	
	@Column(name="due_date")
    @JsonProperty("dueDate")
    private Date dueDate;


    @ManyToOne
    @JoinColumn(name = "todo_id", referencedColumnName = "Id", insertable = false, updatable = false)
    private Todo todo;
    
    public TodoReminder() {
    }
    
    public TodoReminder(int Id, String label, Date date) {
      //  this.id=id;
        this.todo_id = Id;
        this.label = label;
        this.dueDate = date;
    }
}
