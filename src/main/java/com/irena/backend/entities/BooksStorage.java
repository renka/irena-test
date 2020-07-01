package com.irena.backend.entities;

import com.irena.backend.dto.Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "books_storage")
public class BooksStorage extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private int amount;

    @Override
    public Dto toDto() {
        return null;
    }

    public BooksStorage(Book book, int amount) {
        this.book = book;
        this.amount = amount;
    }

    public BooksStorage(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public BooksStorage setAmount(int amount) {
        this.amount = amount;
        return this;
    }
}
