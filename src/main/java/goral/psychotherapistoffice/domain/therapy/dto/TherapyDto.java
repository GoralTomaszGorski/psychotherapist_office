package goral.psychotherapistoffice.domain.therapy.dto;

import java.math.BigDecimal;

public class TherapyDto {

    private Long id;
    private String kindOfTherapy;
    private String description;
    private BigDecimal price;


    public TherapyDto(Long id, String kindOfTherapy, String description, BigDecimal price) {
        this.id = id;
        this.kindOfTherapy = kindOfTherapy;
        this.description = description;
        this.price = price;
    }

    public TherapyDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKindOfTherapy() {
        return kindOfTherapy;
    }

    public void setKindOfTherapy(String kindOfTherapy) {
        this.kindOfTherapy = kindOfTherapy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
