package es.carlop.spring5webapp.bootstrap;

import es.carlop.spring5webapp.model.Author;
import es.carlop.spring5webapp.model.Book;
import es.carlop.spring5webapp.model.Publisher;
import es.carlop.spring5webapp.repositories.AuthorRepository;
import es.carlop.spring5webapp.repositories.BookRepository;
import es.carlop.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        // Eric
        Publisher hc = new Publisher("Harper Collins", "Fake Street S/N");
        publisherRepository.save(hc);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Deisgn", "1234", hc);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        // Rod
        Publisher worx = new Publisher("Worx", "Another Fake Street S/N");
        publisherRepository.save(worx);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "2344", worx);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }
}
