package br.com.carv.reactiveexample.dto.request;

import java.math.BigDecimal;

public class BeerPostRequest {

    private String beerName;
    private String upc;
    private Integer quantityOnHands;
    private BigDecimal unitPrice;

    public BeerPostRequest(String beerName, String upc, Integer quantityOnHands, BigDecimal unitPrice) {
        this.beerName = beerName;
        this.upc = upc;
        this.quantityOnHands = quantityOnHands;
        this.unitPrice = unitPrice;
    }

    public String getBeerName() {
        return beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public Integer getQuantityOnHands() {
        return quantityOnHands;
    }

    public void setQuantityOnHands(Integer quantityOnHands) {
        this.quantityOnHands = quantityOnHands;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "BeerPostRequest{" +
                "beerName='" + beerName + '\'' +
                ", upc='" + upc + '\'' +
                ", quantityOnHands=" + quantityOnHands +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
