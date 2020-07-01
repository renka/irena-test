package com.irena.backend.dto;

import java.math.BigDecimal;

public class BookDto implements Dto {
    private long id;
    private String title;
    private String author;
    private BigDecimal price;
    private String genre;
    private int amountAtStorage;

    public BookDto(String title, String author, String genre, BigDecimal price, long id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public BookDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookDto setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BookDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public BookDto setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public int getAmountAtStorage() {
        return amountAtStorage;
    }

    public BookDto setAmountAtStorage(int amountAtStorage) {
        this.amountAtStorage = amountAtStorage;
        return this;
    }
}
