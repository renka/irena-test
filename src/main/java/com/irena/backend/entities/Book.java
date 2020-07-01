package com.irena.backend.entities;

import com.irena.backend.dto.BookDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "books")
public class Book extends BaseEntity {
    private String title;
    private String author;
    private BigDecimal price;
    private String genre;

    public Book(String title, String author, BigDecimal price, String genre) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.genre = genre;
    }

    public Book(BookDto bookDto) {
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
        this.price = bookDto.getPrice();
        this.genre = bookDto.getGenre();
    }

    @Override
    public BookDto toDto() {
        return new BookDto(this.title, this.author, this.genre, this.price, this.getId());
    }

    public BigDecimal getPrice() {
        return this.price;
    }
}
