package hh.swd20.BookReview.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.BookReview.domain.Book;
import hh.swd20.BookReview.domain.BookRepository;
import hh.swd20.BookReview.domain.Review;
import hh.swd20.BookReview.domain.ReviewRepository;
import hh.swd20.BookReview.domain.User;
import hh.swd20.BookReview.domain.UserRepository;

@Controller
public class ReviewController {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@Autowired
	private UserRepository userRepo;

	
	// Pricipal-rajapinta mahdollistaa kirjautumistunnuksen talteensaamisen
	// Eli arvostelun tekijä on sama kuin sisäänkirjautunut käyttäjä
	
	
	@GetMapping("/myReviews")
	public String showUsersReviews(Model model, Principal principal) {
		
		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		model.addAttribute("reviews", reviewRepo.findAllByReviewer(user));
		return "reviews";
	}
	
	@GetMapping("book/review/{title}")
	public String writeNewReview(@PathVariable("title") String bookTitle, Model model, 
			Principal principal) {
		String username = principal.getName();
		model.addAttribute("user", userRepo.findByUsername(username));
		model.addAttribute("book", bookRepo.findByTitle(bookTitle));
		model.addAttribute("review", new Review());
		return "addReview";
	}
	
	@GetMapping("delete/{id}")
	public String deleteOwnReview(@PathVariable("id") Long reviewId) {
		reviewRepo.deleteById(reviewId);
		return "redirect:../myReviews";
	}
	
	
	@GetMapping("book/delete/{bookTitle}/{reviewId}")
	@PreAuthorize("hasAuthority('ADMIN')")
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
	public String saveReview(Review review, Book book, User user) {
		//review.setBook(book);
		
		// toimii kait
		// haetaan reposta kirja, jonka title on näkymän attribuutteihin lisätyn kirja-olion title
		// asetetaan tämä kirja Review-olion muuttujaan setterillä
		// talletetaan Review-olio repoon
		User user1 = userRepo.findByUsername(user.getUsername());
		Book book1 = bookRepo.findByTitle(book.getTitle());
		//userRepo.save(user);
		review.setBook(book1);
		review.setReviewer(user1);
		reviewRepo.save(review);
		book.addNewReview(review);
		
//		bookRepo.save(book);
//		review.setBook(book);
//		reviewRepo.save(review);
//		book.addNewReview(review);
//		bookRepo.save(bookRepo.findByTitle(book.getTitle()));
		
		// Eläinten vallankumous ei tule osoitekenttään niinkuin pitäisi ? Ääkköset syynä ?
		return "redirect:book/" + book.getTitle();
	}
}
