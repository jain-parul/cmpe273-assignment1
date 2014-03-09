package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Review;
@JsonPropertyOrder({"reviews","links"})
public class ReviewsDto extends LinksDto {
	
	private ArrayList<Review> reviews = new ArrayList<Review>();
	
	public ReviewsDto(ArrayList<Review> reviews){
		this.reviews = reviews;
	}

	public ArrayList<Review> getReviews(){
		return reviews;
	}
}
