package hh.swd20.BookReview;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.swd20.BookReview.web.BookController;
import hh.swd20.BookReview.web.ReviewController;

@SpringBootTest
class BookReviewApplicationTests {

	@Autowired
	private BookController bookController;
	
	@Autowired
	private ReviewController reviewController;
	
	@Test
	public void contextLoads() throws Exception {
		
		assertThat(bookController).isNotNull();
		
	}
	
	@Test
	public void reviewControllerIsCreated() throws Exception {
		assertThat(reviewController).isNotNull();
	}
	
}
