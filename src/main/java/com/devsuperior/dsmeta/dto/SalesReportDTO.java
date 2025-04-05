package com.devsuperior.dsmeta.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class SalesReportDTO implements Serializable {
    private final Long id;
    private final LocalDate date;
    private final Double amount;
    private final String sellerName;

    public SalesReportDTO(Long id, LocalDate date, Double amount, String sellerName){
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
