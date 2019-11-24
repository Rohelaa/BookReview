package hh.swd20.BookReview.domain;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

	// metodi palauttaa olion, jonka tietotyyppi on 'Book'
	
	Book findByTitle(String title);
	Book findByBookId(Long id);
	
	
}
