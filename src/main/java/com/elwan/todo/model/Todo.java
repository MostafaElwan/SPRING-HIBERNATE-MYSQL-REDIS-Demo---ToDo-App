package com.elwan.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.elwan.todo.common.AppConstant;


@Data
@Entity
@Table(name = "todo_list")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region=AppConstant.Hibernate.Cache.Region.TODO)
public class Todo {
	
	public static Todo WELCOME = new Todo(0, "Welcome Todo", "This is a description of a welcome todo");
	
	public Todo(){ }
	
	public Todo(long id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
	}
	
	@Id
	@Column(name = "todo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "todo_title")
	private String title;
	
	@Column(name = "todo_desc")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "todo_owner_id")
	private User user;

	@Override
	public String toString() {
		return String.format("[id=%s, title=%s, description=%s]", id, title, description);
	}
}
