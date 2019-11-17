package hh.swd20.BookReview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;

import hh.swd20.BookReview.domain.Book;
import hh.swd20.BookReview.domain.BookRepository;
import hh.swd20.BookReview.domain.Category;
import hh.swd20.BookReview.domain.CategoryRepository;
import hh.swd20.BookReview.domain.Review;
import hh.swd20.BookReview.domain.UserRepository;

@SpringBootApplication
public class BookReviewApplication {
	
	public static final Logger logger = LoggerFactory.getLogger(BookReviewApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookReviewApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner addCoupleOfBooks(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		
		return (args) -> {
			
			logger.info("Added few categories.");
			categoryRepository.save(new Category("Realismi"));
			categoryRepository.save(new Category("Allegoria"));
			categoryRepository.save(new Category("Historiallinen romaani"));
			
			logger.info("Added books.");
			bookRepository.save(new Book("Eläinten vallankumous", "George Orwell", "1945", categoryRepository.findByName("Allegoria")));
			Book book2 = new Book("Rikos ja rangaistus", "F.M. Dostojevski", "1866", categoryRepository.findByName("Realismi"));
			
			
			logger.info("Added reviews");
			book2.addNewReview(new Review("Roope Laakso", "5", "Jee"));
			book2.addNewReview(new Review("ASDADS", "4", "asdojasodasodjoasdjoasdoasdjoas"));
			book2.addNewReview(new Review("Matti", "5", "Sikahyvä!!", book2));
			
			logger.info("Users created.");
			userRepository.save(new hh.swd20.BookReview.domain.User("user", "$2a$10$F5sj4llIzBZ427pgz7Vgn.fDK2AMQhmQ0.H/v/TlAdThKdky7sSja", "USER"));
			userRepository.save(new hh.swd20.BookReview.domain.User("admin", "$2y$10$rnaTQTo1yBBFGwjvqaCNbuPMqt4POEFtO.Qh.8FlKS8y6lPaSboCC", "ADMIN"));
			
			
			bookRepository.save(book2);
			
			
		};
	}

}
