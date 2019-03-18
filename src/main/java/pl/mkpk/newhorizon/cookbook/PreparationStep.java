package pl.mkpk.newhorizon.cookbook;

import java.math.BigDecimal;
import java.util.List;

public class PreparationStep {

    public String description;
    public BigDecimal minutes;

    public PreparationStep(String description, BigDecimal minutes) {
        this.description = description;
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return ""+ description +" ("+ minutes +" min)";
    }
}
