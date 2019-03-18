package pl.mkpk.newhorizon.cookbook;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.List;

@Embeddable
public class PreparationStep {

    public String description;
    public BigDecimal minutes;

    public PreparationStep(String description, BigDecimal minutes) {
        this.description = description;
        this.minutes = minutes;
    }

    public PreparationStep() {
    }

    @Override
    public String toString() {
        return ""+ description +" ("+ minutes +" min)";
    }
}
