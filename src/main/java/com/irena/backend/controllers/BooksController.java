package com.irena.backend.controllers;

import com.irena.backend.dto.BookDto;
import com.irena.backend.dto.PurchaseDto;
import com.irena.backend.entities.Book;
import com.irena.backend.services.BookService;
import com.irena.backend.services.UserService;
import com.irena.backend.utils.BaseResponse;
import com.irena.backend.utils.Constants;
import com.irena.backend.utils.ErrorHandlingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = Constants.UI_HOST, maxAge = Constants.CORS_AGE)
@RestController
public class BooksController {

    private static final Logger log = LoggerFactory.getLogger(BooksController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("books")
    public BaseResponse<List<Book>> getBooks(String category) {
        List<BookDto> books = bookService.getBooks();
        return getListResponse(books);
    }

    @GetMapping("books/{id}")
    public BaseResponse<BookDto> getBook(@PathVariable Long id) {
        try {
            BookDto book = bookService.getBook(id);
            return new BaseResponse<>(book);
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, "No book found", log);
        }
    }

    @PostMapping("books/add")
    public BaseResponse<BookDto> addBook(@RequestBody BookDto bookDto) {
        try {
            return new BaseResponse<>(bookService.addBook(bookDto));
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, "Error saving book", log);
        }
    }

    @PostMapping("books/checkout")
    public BaseResponse<PurchaseDto> checkoutBook(@RequestBody PurchaseDto purchaseDto) {
        try {
            PurchaseDto purchase = bookService.checkoutBook(purchaseDto);
            return new BaseResponse<>(purchase);
        } catch (Exception e) {
            return ErrorHandlingUtil.getErrorResponse(e, "Error occured", log);
        }
    }


    private BaseResponse getListResponse(List data) {
        if (data.isEmpty()) {
            return new BaseResponse<>(null,"No items found");
        }
        return new BaseResponse<>(data, "Success");
    }
}
