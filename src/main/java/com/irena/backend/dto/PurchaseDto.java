package com.irena.backend.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class PurchaseDto implements Dto {
    private long id;
    private String purchaseId;
    private long userId;
    private long bookId;
    private int numberOfBooks;
    private BigDecimal amountPaid;
    private Timestamp date;

    public PurchaseDto(long id, String purchaseId, long userId, long bookId, int numberOfBooks, BigDecimal amountPaid, Timestamp date) {
        this.id = id;
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.bookId = bookId;
        this.numberOfBooks = numberOfBooks;
        this.amountPaid = amountPaid;
        this.date = date;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public PurchaseDto setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public PurchaseDto setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getBookId() {
        return bookId;
    }

    public PurchaseDto setBookId(long bookId) {
        this.bookId = bookId;
        return this;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public PurchaseDto setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
        return this;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public PurchaseDto setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    public Timestamp getDate() {
        return date;
    }

    public PurchaseDto setDate(Timestamp date) {
        this.date = date;
        return this;
    }
}
