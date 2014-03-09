package edu.sjsu.cmpe.library.domain;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class Author {

	private long id;
	@NotEmpty
	@Valid
	private String name;
	
	public void setId(long authorId){
		this.id = authorId;
	}
	
	public long getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String authorName){
		this.name = authorName;
	}
		
}
