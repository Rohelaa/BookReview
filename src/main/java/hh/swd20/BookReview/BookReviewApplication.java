package hh.swd20.BookReview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.BookReview.domain.Book;
import hh.swd20.BookReview.domain.BookRepository;
import hh.swd20.BookReview.domain.Category;
import hh.swd20.BookReview.domain.CategoryRepository;

@SpringBootApplication
public class BookReviewApplication {
	
	public static final Logger logger = LoggerFactory.getLogger(BookReviewApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookReviewApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner addCoupleOfBooks(BookRepository bookRepository, CategoryRepository categoryRepository) {
		
		return (args) -> {
			
			logger.info("Added few categories.");
			categoryRepository.save(new Category("Realismi"));
			categoryRepository.save(new Category("Allegoria"));
			
			logger.info("Added books.");
			bookRepository.save(new Book("Eläinten vallankumous", "George Orwell", "1945", categoryRepository.findByName("Allegoria")));
			bookRepository.save(new Book("Rikos ja rangaistus", "F.M Dostojevski", "1866", categoryRepository.findByName("Realismi")));
			
			
		};
	}

}
