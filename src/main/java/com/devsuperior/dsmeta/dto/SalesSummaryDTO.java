package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SalesSummaryProjection;

import java.io.Serializable;

public class SalesSummaryDTO implements Serializable {
    private final String sellerName;
    private final Double total;

    public SalesSummaryDTO(String sellerName, Double total){
        this.sellerName = sellerName;
        this.total = total;
    }

    public SalesSummaryDTO(SalesSummaryProjection projection){
        this(projection.getSeller(), projection.getTotal());
    }

    public String getSellerName(){
        return sellerName;
    }

    public Double getTotal(){
        return total;
    }
}
