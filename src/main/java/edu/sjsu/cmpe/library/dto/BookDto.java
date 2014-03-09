package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.BookDetails;

@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto {
    private BookDetails book_details;

    /**
     * @param book
     */
    public BookDto(BookDetails book_details) {
	super();
	this.book_details = book_details;
    }

    /**
     * @return the book
     */
    public BookDetails getBook() {
	return book_details;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(BookDetails book_details) {
	this.book_details = book_details;
    }
}
