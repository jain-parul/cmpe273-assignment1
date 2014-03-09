package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {
	/** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public ReviewResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }
    
    @GET
    @Timed(name = "view-all-reviews")
    public Response viewAllReviews(@PathParam("isbn") LongParam isbn) {
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	ArrayList<Review> reviews = book.getReviews();
    	
    	String location = "/books/" + isbn + "/reviews";
    	ReviewsDto reviewResponse = new ReviewsDto(reviews);
    	reviewResponse.addLink(new LinkDto("view-review", location, "GET"));
    	
    	return Response.status(201).entity(reviewResponse).build();
    }
    
    @GET
    @Path("/{reviewId}")
    @Timed(name = "view-review")
    public Response viewReview(@PathParam("isbn") LongParam isbn, @PathParam("reviewId") long reviewId) {
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	ArrayList<Review> reviews = book.getReviews();
    	int i = 0;
    	while(i!=reviewId){
    		i++;
    	}
    	Review review = reviews.get(i-1);
    	ReviewDto reviewResponse = new ReviewDto(review);
    	   	
    	return Response.status(201).entity(reviewResponse).build();
    }
    
    @POST
    @Timed(name = "create-review")
    public Response createReview(@PathParam("isbn") LongParam isbn,@Valid Review newReview) {
    Book book = bookRepository.getBookByISBN(isbn.get());
    System.out.println(newReview.getRating());
    System.out.println(newReview.getComment());
    	if(newReview.getRating() < 1 || newReview.getRating() > 5 || newReview.getComment() == null || newReview.getComment() == "")
    		return Response.status(400).entity("400 Bad Request: Rating [0-5] and comments are required fields").build();	
    	else{	
    		ArrayList<Review> reviews = book.getReviews();
    			long reviewId = reviews.size();
    					++reviewId;
    					newReview.setId(reviewId);
    					reviews.add(newReview);
    	
    					String location = "/books/" + isbn + "/reviews/" + reviewId;
    						//ReviewsDto reviewResponse = new ReviewsDto(reviews);
    					LinksDto reviewResponse = new LinksDto();
    					reviewResponse.addLink(new LinkDto("view-review", location, "GET"));
	
    		return Response.status(201).entity(reviewResponse).build();
    		}
    }  	
}
   
    
