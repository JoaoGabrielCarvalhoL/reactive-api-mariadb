package br.com.carv.reactiveexample.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Table("tb_beer")
public class Beer implements Persistable<Long> {
    @Id
    private Long id;
    @Version
    private Long version;
    @Column("beer_name")
    private String beerName;
    private String upc;
    @Column("quantity_on_hands")
    private Integer quantityOnHands;
    @Column("unit_price")
    private BigDecimal unitPrice;

    private OffsetDateTime createdDate;
    private OffsetDateTime updatedDate;

    public Beer() {

    }

    public Beer(Long id, Long version, String beerName, String upc, Integer quantityOnHands,
                BigDecimal unitPrice, OffsetDateTime createdDate, OffsetDateTime updatedDate) {
        this.id = id;
        this.version = version;
        this.beerName = beerName;
        this.upc = upc;
        this.quantityOnHands = quantityOnHands;
        this.unitPrice = unitPrice;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Beer(Long version, String beerName, String upc, Integer quantityOnHands, BigDecimal unitPrice, OffsetDateTime createdDate, OffsetDateTime updatedDate) {
        this.version = version;
        this.beerName = beerName;
        this.upc = upc;
        this.quantityOnHands = quantityOnHands;
        this.unitPrice = unitPrice;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Beer(String beerName, String upc, Integer quantityOnHands, BigDecimal unitPrice) {
        this.beerName = beerName;
        this.upc = upc;
        this.quantityOnHands = quantityOnHands;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        Boolean result = this.id == null ? true : false;
        return result;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    public OffsetDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public OffsetDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(OffsetDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
