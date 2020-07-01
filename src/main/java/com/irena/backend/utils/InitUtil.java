package com.irena.backend.utils;

import com.github.javafaker.Faker;
import com.irena.backend.entities.Book;
import com.irena.backend.entities.BooksStorage;
import com.irena.backend.entities.User;
import com.irena.backend.repos.BookRepository;
import com.irena.backend.repos.BooksStorageRepository;
import com.irena.backend.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class InitUtil {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BooksStorageRepository booksStorageRepository;

    public void init() {
        Faker faker = new Faker();
        userRepository.save(new User("admin", "admin"));

         for (int i = 0; i < 50; i++) {
            BigDecimal randomPrice = BigDecimal.valueOf(Math.random());
            Book book = new Book(faker.book().title(),
                            faker.book().author(),
                            randomPrice,
                            faker.book().genre());
             bookRepository.save(book);
             booksStorageRepository.save(new BooksStorage(book, ThreadLocalRandom.current().nextInt(0, 1001)));
         }
    }
}
