package hh.swd20.BookReview;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.BookReview.domain.Book;
import hh.swd20.BookReview.domain.BookRepository;
import hh.swd20.BookReview.domain.Category;
import hh.swd20.BookReview.domain.CategoryRepository;
import hh.swd20.BookReview.domain.Review;
import hh.swd20.BookReview.domain.ReviewRepository;
import hh.swd20.BookReview.domain.User;
import hh.swd20.BookReview.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTests {

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void addingANewReviewGeneratesAnId() {
		User user = new User("user", "password", "user");
		userRepo.save(user);
		Category category = new Category("kategoria1");
		categoryRepository.save(category);
		Book book = new Book("book1", "author1", "2000", category);
		bookRepo.save(book);
		Review newReview = new Review(user, "5", "Very good", book);
		reviewRepo.save(newReview);
		assertThat(newReview.getId()).isNotNull();
	}

	@Test
	public void AddingOneReviewToRepoReturnsOneReview() {
		User user1 = new User("user1", "password1", "user1");
		userRepo.save(user1);
		Category category = new Category("kategoria1");
		categoryRepository.save(category);
		Book book = new Book("book1", "author1", "2000", category);
		bookRepo.save(book);
		Review newReview = new Review(user1, "5", "Very good", book);
		reviewRepo.save(newReview);
		assertThat(reviewRepo.findAllByReviewer(user1).size() == 1);
	}
	
	
}
