package hh.swd20.BookReview.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {

	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long reviewId;
	
	@NotNull
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "userId")
	private User reviewer;
	

	//@Min(value = 1) @Max(value = 5)
	private String rating;
	
	@Size(min = 1, max = 5000)
	private String reviewText;
	
	@NotNull
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book book;
	
	
	public Review() {}

	public Review(User reviewer, String rating, String reviewText) {
		super();
		this.reviewer = reviewer;
		this.rating = rating;
		this.reviewText = reviewText;
	}
	
	public Review(User reviewer, String rating, String reviewText, Book book) {
		this.reviewer = reviewer;
		this.rating = rating;
		this.reviewText = reviewText;
		this.book = book;
	}

	public Long getId() {
		return reviewId;
	}

	public void setId(Long id) {
		this.reviewId = id;
	}

	public User getReviewer() {
		return reviewer;
	}

	public void setReviewer(User reviewer) {
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

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", reviewer=" + reviewer + ", rating=" + rating + ", reviewText="
				+ reviewText + ", book=" + book + "]";
	}
	
	
	
	
	
}
