package hh.swd20.BookReview.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.swd20.BookReview.domain.Book;
import hh.swd20.BookReview.domain.BookRepository;
import hh.swd20.BookReview.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;

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
		return "addBook";
	}
	
	@PostMapping("/saveBook")
	public String saveNewBook(Book book) {
		bookRepo.save(book);
		return "redirect:books";
	}
}
