package com.irena.backend.entities;

import com.irena.backend.dto.PurchaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "purchases")
public class Purchase extends BaseEntity{
    private String purchaseId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private int numberOfBooks;
    private BigDecimal amountPaid;
    private Timestamp date;

    public Purchase(String purchaseId, User user, Book book, int numberOfBooks, BigDecimal amountPaid, Timestamp date) {
        this.purchaseId = purchaseId;
        this.user = user;
        this.book = book;
        this.numberOfBooks = numberOfBooks;
        this.amountPaid = amountPaid;
        this.date = date;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    @Override
    public PurchaseDto toDto() {
        return new PurchaseDto(
                this.getId(),
                this.purchaseId,
                this.user.getId(),
                this.book.getId(),
                this.numberOfBooks,
                this.amountPaid,
                this.date
        );
    }
}
