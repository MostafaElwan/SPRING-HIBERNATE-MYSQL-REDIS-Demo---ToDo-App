package com.elwan.todo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.elwan.todo.common.AppConstant;

@Data
@Entity
@Table(name = "users")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region=AppConstant.Hibernate.Cache.Region.TODO)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "encrypted_password")
	private String password;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "unique_id")
	private String uniqueId;
	
	@Column(name = "salt")
	private String salt;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Todo> todoList;

}
