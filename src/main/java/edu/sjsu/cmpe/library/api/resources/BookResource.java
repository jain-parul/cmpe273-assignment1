package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.BookDetails;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    /** bookRepository instance */
    private final BookRepositoryInterface bookRepository;

    /**
     * BookResource constructor
     * 
     * @param bookRepository
     *            a BookRepository instance
     */
    public BookResource(BookRepositoryInterface bookRepository) {
	this.bookRepository = bookRepository;
    }

    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") LongParam isbn) {
	Book book = bookRepository.getBookByISBN(isbn.get());
	BookDetails book_details = new BookDetails(book);
	BookDto bookResponse = new BookDto(book_details);
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(), "GET"));
	bookResponse.addLink(new LinkDto("update-book", "/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book",	"/books/" + book.getIsbn(), "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", "/books/" + book.getIsbn() + "/reviews", "POST"));
	bookResponse.addLink(new LinkDto("view-all-reviews", "/books/" + book.getIsbn() + "/reviews", "GET"));
	// add more links

	return bookResponse;
    }

    @GET
    @Path("/{isbn}/authors/{id}")
    @Timed(name = "view-author")
    public Response viewReview(@PathParam("isbn") LongParam isbn, @PathParam("id") long id) {
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	ArrayList<Author> authors = book.getAuthors();
    	int i = 0;
    	while(i!=id){
    		i++;
    	}
    	Author author = authors.get(i-1);
    	
    	String location = "/books/" + isbn + "/authors/" + i;
    	AuthorDto authorResponse = new AuthorDto(author);
    	authorResponse.addLink(new LinkDto("view-author", location, "GET"));
    	
    	return Response.status(201).entity(authorResponse).build();
    }
    
    @GET
    @Path("/{isbn}/authors")
    @Timed(name = "view-all-authors")
    public Response viewAllAuthors(@PathParam("isbn") LongParam isbn) {
    	Book book = bookRepository.getBookByISBN(isbn.get());
    	ArrayList<Author> authors = book.getAuthors();
    	
    	AuthorsDto authorResponse = new AuthorsDto(authors);
    	return Response.status(201).entity(authorResponse).build();
    }
    
    @POST
    @Timed(name = "create-book")
    public Response createBook(@Valid Book request) {
	// Store the new book in the BookRepository so that we can retrieve it.
    Book savedBook = bookRepository.saveBook(request);

	String location = "/books/" + savedBook.getIsbn();
	//BookDto bookResponse = new BookDto(savedBook);
	LinksDto bookResponse = new LinksDto();
	
	bookResponse.addLink(new LinkDto("view-book", location, "GET"));
	bookResponse.addLink(new LinkDto("update-book", location, "PUT"));
	bookResponse.addLink(new LinkDto("delete-book", location, "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", location+"/reviews", "POST"));
	// Add other links if needed

	return Response.status(201).entity(bookResponse).build();
    } 
    
    @DELETE
    @Path("/{isbn}")
    @Timed(name = "delete-book")
    public Response deleteBookByIsbn(@PathParam("isbn") LongParam isbn) {
	bookRepository.deleteBook(isbn.get());

	String location = "/books";
	//BookDto bookResponse = new BookDto(deletedBook);
	LinksDto bookResponse = new LinksDto();
	bookResponse.addLink(new LinkDto("create-book", location, "POST"));
	
	return Response.status(201).entity(bookResponse).build();
    }
    
    @PUT
    @Path("/{isbn}")
    @Timed(name = "update-book")
    public Response updateBookByIsbn(@PathParam("isbn") LongParam isbn, @QueryParam("status") String status) {
    Book book = bookRepository.getBookByISBN(isbn.get());
    book.setStatus(status);
    
    LinksDto bookResponse = new LinksDto();
	bookResponse.addLink(new LinkDto("view-book", "/books/" + book.getIsbn(), "GET"));
	bookResponse.addLink(new LinkDto("update-book", "/books/" + book.getIsbn(), "PUT"));
	bookResponse.addLink(new LinkDto("delete-book",	"/books/" + book.getIsbn(), "DELETE"));
	bookResponse.addLink(new LinkDto("create-review", "/books/" + book.getIsbn() + "/reviews", "POST"));
	bookResponse.addLink(new LinkDto("view-all-reviews", "/books/" + book.getIsbn() + "/reviews", "GET"));
    
	return Response.status(201).entity(bookResponse).build();
    }
}

