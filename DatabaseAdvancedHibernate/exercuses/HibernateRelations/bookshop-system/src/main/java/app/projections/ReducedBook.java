package app.projections;

import app.domain.enums.AgeRestriction;
import app.domain.enums.EditionType;

import java.math.BigDecimal;

public interface ReducedBook {
    String getTitle();

    void setTitle(String title);

    EditionType getEditionType();

    void setEditionType(EditionType editionType);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    AgeRestriction getAgeRestriction();

    void setAgeRestriction(AgeRestriction ageRestriction);
}
