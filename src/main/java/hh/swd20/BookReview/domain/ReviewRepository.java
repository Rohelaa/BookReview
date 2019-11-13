package hh.swd20.BookReview.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
	
	List<Review> findAllByBookTitle(String bookTitle);

}
