package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe.library.dto.LinkDto;

public class BookDetails {
	private long isbn;
    @NotEmpty
    @NotNull
    private String title;
    @NotEmpty
    @NotNull
    private String publicationDate;
    private String language;
    private long numPages;
    private String status;
    ArrayList<LinkDto> reviews = new ArrayList<LinkDto>();
	@NotEmpty
	@NotNull
	ArrayList<LinkDto> authors = new ArrayList<LinkDto>();
	
	public BookDetails(Book book) {
		this.isbn = book.getIsbn();
		this.title = book.getTitle();
		this.publicationDate = book.getpublicationDate();
		this.language = book.getLanguage();
		this.numPages = book.getnumPages();
		this.status = book.getStatus();

		for (int i = 0; i < book.reviews.size(); i++) {
			this.reviews.add(new LinkDto("view-review",
					"/books/" + book.getIsbn() + "/reviews"
							+ book.reviews.get(i).getId(), "GET"));
		}

		for (int i = 0; i < book.authors.size(); i++) {
			this.authors.add(new LinkDto("view-author", "/books/" + book.getIsbn()
					+ "/authors" + book.authors.get(i).getId(), "GET"));
		}
	}

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
	 * @param num
	 *            pages the num pages to set
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
	public ArrayList<LinkDto> getAuthors() {
		return authors;
	}

	/**
	 * @param authors
	 *            the authors to set
	 */
	public void setAuthors(ArrayList<LinkDto> authors) {
		this.authors = authors;
	}

	/**
	 * @return the reviews
	 */
	public void setReviews(ArrayList<LinkDto> reviews) {
		this.reviews = reviews;
	}

	/**
	 * @param reviews
	 *            the reviews to set
	 */
	public ArrayList<LinkDto> getReviews() {
		return reviews;
	}
}
