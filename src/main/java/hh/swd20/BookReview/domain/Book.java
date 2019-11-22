package hh.swd20.BookReview.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {


	//@NotNull

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long bookId;
	
	@Size(min = 1, max = 50)
	private String title;
	
	@Size(min = 1, max = 50)
	private String author;
	
	@Size(min = 1, max = 4)
	private String year;
	
	@NotNull
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	private List<Review> reviews;
	
	public Book() {
		this.reviews = new ArrayList<Review>();
	}

	public Book(String title, String author, String year, Category category) {
		super();
		this.reviews = new ArrayList<Review>();
		this.title = title;
		this.author = author;
		this.year = year;
		this.category = category;
		
	}
	
	public void addNewReview(Review review) {
		this.reviews.add(review);
	}

	public Long getId() {
		return bookId;
	}

	public void setId(Long id) {
		this.bookId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", year=" + year + ", category="
				+ category.getName() + ", reviews=" + this.getReviews() + "]";
	}
	
	
	
	
}
