package edu.sjsu.cmpe.library.domain;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class Review {
	
	private long id = 0;
	private long rating;
	String comment;
	
	public long getId(){
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getRating(){
		return rating;
	}
	
	public void setRating(long rating){
		this.rating = rating;
	}
	
	public String getComment(){
		return comment;
	}
	
	public void setComment(String comment){
		this.comment = comment;
	}

}
