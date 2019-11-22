package hh.swd20.BookReview.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.BookReview.domain.Book;
import hh.swd20.BookReview.domain.BookRepository;
import hh.swd20.BookReview.domain.Category;
import hh.swd20.BookReview.domain.CategoryRepository;
import hh.swd20.BookReview.domain.ReviewRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	
	// Login & logout
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/logOut")
	public String logOut() {
		return "redirect:login?logout";
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/books")
	public String getBookPage(Model model) {
		model.addAttribute("books", bookRepo.findAll());
		return "books";
	}
	
	@GetMapping("/addBook")
	public String addNewBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("category", new Category());
		return "addBook";
	}
	
	// BindingResult olio tarkistaa, ovatko lomakkeeseen syötetyt tiedot annettujen ehtojen mukaiset
	
	@PostMapping("/saveBook")
	public String saveNewBook(@Valid Book book , Category category, BindingResult bindingResult) {
		
		

		
		// Luotu kategoria on ensin tallennettava repoon, jotta sen voi setterillä tallettaa olion muuttujaan
		categoryRepo.save(category);
		
		if (category != null) {
			book.setCategory(category);
		}
		
		if (bindingResult.hasErrors()) {
			return "redirect:addBook";
		}
		
		bookRepo.save(book);
		return "redirect:books";
	}
	
	@GetMapping("/book/{title}")
	public String showBookInformation(@PathVariable(name = "title") String bookTitle, Model model) {
		model.addAttribute("book", bookRepo.findByTitle(bookTitle));
		// model.addAttribute("reviews", reviewRepo.findAll());
		model.addAttribute("reviews", reviewRepo.findAllByBookTitle(bookTitle));
		return "bookInformation";
	}
	
	
	// REST 
	
	
	@GetMapping("/booksRest")
	public @ResponseBody List<Book> getBooksRest() {
		 return (List<Book>) bookRepo.findAll();
	}
	
	@GetMapping("/booksRest/{id}")
	public @ResponseBody Optional<Book> getBookRest(@PathVariable(name = "id") Long id)  {
		return bookRepo.findById(id);
	}
	
	
	
}
