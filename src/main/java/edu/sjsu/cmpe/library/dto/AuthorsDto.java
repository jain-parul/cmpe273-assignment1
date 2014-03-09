package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import edu.sjsu.cmpe.library.domain.Author;

public class AuthorsDto extends LinksDto {
	
	private ArrayList<Author> authors = new ArrayList<Author>();
	
	public AuthorsDto(ArrayList<Author> authors){
		this.authors = authors;
	}

	public ArrayList<Author> getAuthors(){
		return authors;
	}
}
