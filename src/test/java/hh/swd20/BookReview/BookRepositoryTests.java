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

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTests {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Test
	public void AddingABookToRepoGeneratesId() {
		Category category = new Category("Kategoria1");
		categoryRepo.save(category);
		Book newBook = new Book("Kirja1", "Kirjailija1", "1", category);
		bookRepository.save(newBook);
		assertThat(newBook.getId()).isNotNull();
	}
	
	@Test
	public void bookCanBeFoundByItsNameFromRepository() {
		Category newCategory = new Category("kategoria");
		categoryRepo.save(newCategory);
		Book newBook = new Book("book", "author", "2000", newCategory);
		bookRepository.save(newBook);
		Book searchResult = bookRepository.findByTitle("book");
		assertThat(searchResult).isNotNull();
	}
	
	@Test
	public void newBookContainsNoReviews() {
		Category newCategory = new Category("kategoria");
		categoryRepo.save(newCategory);
		Book newBook = new Book("book", "author", "2000", newCategory);
		assertThat(newBook.getReviews().size()).isEqualTo(0);
	}
	
	
}
