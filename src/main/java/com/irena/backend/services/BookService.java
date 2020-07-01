package com.irena.backend.services;

import com.irena.backend.dto.BookDto;
import com.irena.backend.dto.PurchaseDto;
import com.irena.backend.entities.Book;
import com.irena.backend.entities.BooksStorage;
import com.irena.backend.entities.Purchase;
import com.irena.backend.entities.User;
import com.irena.backend.repos.BookRepository;
import com.irena.backend.repos.BooksStorageRepository;
import com.irena.backend.repos.PurchaseRepository;
import com.irena.backend.utils.exceptions.BookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    private static final int START_RANDOM_TIMESTAMP = 2;
    private static final int END_RANDOM_TIMESTAMP = 10;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BooksStorageRepository booksStorageRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(Book::toDto).collect(Collectors.toList());
     }

    public BookDto getBook(long bookId) {
        log.info("getBook");
        Book book = getBookEntity(bookId);
        BooksStorage books = getBooksStorage(bookId);
        return book.toDto()
                .setAmountAtStorage(books.getAmount());
    }

    private BooksStorage getBooksStorage(long bookId) {
        return booksStorageRepository.findByBook_Id(bookId).orElseThrow(() -> new BookException("No Books left at storage"));
    }

    private Book getBookEntity(long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookException("No Book found"));
    }

    public Optional<Book> getBookByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }

    @Transactional
    public BookDto addBook(BookDto bookDto) {
        Optional<Book> book = getBookByTitleAndAuthor(bookDto.getTitle(), bookDto.getAuthor());
        if (book.isPresent()) {
            BooksStorage booksStorage = booksStorageRepository.findByBook_Id(book.get().getId())
                    .orElse(new BooksStorage(book.get()));
            booksStorage.setAmount(bookDto.getAmountAtStorage());
            booksStorageRepository.save(booksStorage);
            return bookDto;
        } else {
            Book newBook = bookRepository.save(new Book(bookDto));
            BooksStorage newBookStorage = booksStorageRepository.save(new BooksStorage(newBook, bookDto.getAmountAtStorage()));
            return newBook.toDto().setAmountAtStorage(newBookStorage.getAmount());
        }
    }

    @Transactional
    public PurchaseDto checkoutBook(PurchaseDto purchaseDto) {
        User user = userService.getUserById(purchaseDto.getUserId());
        Book book = getBookEntity(purchaseDto.getBookId());
        BooksStorage books = getBooksStorage(book.getId());
        BigDecimal amountPaid = book.getPrice().multiply(BigDecimal.valueOf(purchaseDto.getNumberOfBooks()));
        String purchaseId = String.valueOf(System.currentTimeMillis()).substring(START_RANDOM_TIMESTAMP, END_RANDOM_TIMESTAMP);
        Purchase purchase = purchaseRepository.save(new Purchase(purchaseId, user, book, purchaseDto.getNumberOfBooks(), amountPaid, new Timestamp(System.currentTimeMillis())));
        books.setAmount(books.getAmount() - purchase.getNumberOfBooks());
        booksStorageRepository.save(books);
        return purchase.toDto();
    }
}
