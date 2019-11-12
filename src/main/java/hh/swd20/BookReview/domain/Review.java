package hh.swd20.BookReview.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Review {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long reviewId;
	private String reviewer;
	private String rating;
	private String reviewText;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private Book book;
	
	public Review() {}

	public Review(String reviewer, String rating, String reviewText) {
		super();
		this.reviewer = reviewer;
		this.rating = rating;
		this.reviewText = reviewText;
	}

	public Long getId() {
		return reviewId;
	}

	public void setId(Long id) {
		this.reviewId = id;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
}
