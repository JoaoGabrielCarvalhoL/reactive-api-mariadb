package br.com.carv.reactiveexample.dto.request;

import java.math.BigDecimal;

public class BeerPutRequest {

    private Long id;
    private String beerName;
    private String upc;
    private Integer quantityOnHands;
    private BigDecimal unitPrice;

    public BeerPutRequest(Long id, String beerName, String upc, Integer quantityOnHands, BigDecimal unitPrice) {
        this.id = id;
        this.beerName = beerName;
        this.upc = upc;
        this.quantityOnHands = quantityOnHands;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
