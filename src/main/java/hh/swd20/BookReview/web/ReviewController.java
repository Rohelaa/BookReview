package hh.swd20.BookReview.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.BookReview.domain.Book;
import hh.swd20.BookReview.domain.BookRepository;
import hh.swd20.BookReview.domain.Review;
import hh.swd20.BookReview.domain.ReviewRepository;

@Controller
public class ReviewController {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;

	@GetMapping("book/review/{title}")
	public String writeNewReview(@PathVariable("title") String bookTitle, Model model) {
		model.addAttribute("book", bookRepo.findByTitle(bookTitle));
		model.addAttribute("review", new Review());
		return "addReview";
	}
	
	@GetMapping("book/delete/{bookTitle}/{reviewId}")
	public String deleteReview(@PathVariable("bookTitle") String bookTitle, @PathVariable("reviewId") Long reviewId, Model model) {
		
		reviewRepo.deleteById(reviewId);
		Book book = bookRepo.findByTitle(bookTitle);
		List<Review> reviews = book.getReviews();
		
		for (Review review : reviews) {
			if (review.getId() == reviewId) {
				reviews.remove(review);
			}
		}
		
		book.setReviews(reviews);
		
		// takaisin kirjanäkymään
		return "redirect:../../../book/" + bookTitle;
	}
	
	@PostMapping("saveReview")
	public String saveReview(Review review, Book book) {
		//review.setBook(book);
		
		// toimii kait
		// haetaan reposta kirja, jonka title on näkymän attribuutteihin lisätyn kirja-olion title
		// asetetaan tämä kirja Review-olion muuttujaan setterillä
		// talletetaan Review-olio repoon
		
		Book book1 = bookRepo.findByTitle(book.getTitle());
		review.setBook(book1);
		reviewRepo.save(review);
		book.addNewReview(review);
		
//		bookRepo.save(book);
//		review.setBook(book);
//		reviewRepo.save(review);
//		book.addNewReview(review);
//		bookRepo.save(bookRepo.findByTitle(book.getTitle()));
		
		return "redirect:book/" + book.getTitle();
	}
}
