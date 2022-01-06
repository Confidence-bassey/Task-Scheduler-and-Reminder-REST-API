package com.example.demo.domain.entities;

//import java.util.Collection;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import javax.persistence.*;



@Data
@Entity(name = "todo")
public class Todo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name="Id")
    private int id;
	
	@Column
    @JsonProperty("title")
    private String title;
	
	@Column
    @JsonProperty("description")
    private String description;
	
	@Column(name = "created_date")
    @JsonProperty("createdDate")
    private Date createdDate;

    //@OneToMany(targetEntity = TodoReminder.class)
    //private Collection<TodoReminder> Reminders;

    public Todo() {
    }
    
    public Todo(int id, String title, String desc, Date date) {
        this.id=id;
        this.title = title;
        this.description = desc;
        this.createdDate = date;
    }
}
