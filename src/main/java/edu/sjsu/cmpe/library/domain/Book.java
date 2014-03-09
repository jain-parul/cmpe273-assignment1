package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    private long isbn;
    @NotEmpty
    @NotNull
    @JsonProperty ("title")
    private String title;
    @NotEmpty
    @NotNull
    @JsonProperty ("publication-date")
    private String publicationDate;
    @JsonProperty ("language")
    private String language;
    @JsonProperty ("num-pages")
    private long numPages;
    @JsonProperty ("status")
    private String status;
    ArrayList<Review> reviews = new ArrayList<Review>();
    @NotEmpty
    @NotNull
	@JsonProperty ("authors")
    ArrayList<Author> authors;
       
    // add more fields here

    /**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }
    
    /**
     * @return the publicationDate
     */
    public String getpublicationDate() {
	return publicationDate;
    }

    /**
     * @param publicationDate
     *            the publicationDate to set
     */
    public void setpublicationDate(String publicationDate) {
	this.publicationDate = publicationDate;
    }
    
    /**
     * @return the language
     */
    public String getLanguage() {
	return language;
    }
    
    /**
     * @param language
     *            the language to set
     */
    public void setLanguage(String language) {
	this.language = language;
    }
    
    /**
     * @return the num pages
     */
    public long getnumPages() {
	return numPages;
    }

    /**
     * @param num pages
     *            the num pages to set
     */
    public void setnumPages(long numPages) {
	this.numPages = numPages;
    }
    
    /**
     * @return the status
     */
    public String getStatus() {
	return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
    	if(status.isEmpty())
		{
			this.status = "available";
		}
		this.status = status;
    }
    
    /**
     * @return the authors
     */
    public ArrayList<Author> getAuthors() {
	return authors;
    }
    
    /**
     * @param authors
     *            the authors to set
     */
    public void setAuthors(ArrayList<Author> authors) {
    this.authors = authors; 
    }
    
    /**
     * @return the reviews
     */
    public void setReviews(ArrayList<Review> reviews){
    	this.reviews = reviews;
    }
    
    /**
     * @param reviews
     *            the reviews to set
     */
    public ArrayList<Review> getReviews(){
    	return reviews;
    }
}
