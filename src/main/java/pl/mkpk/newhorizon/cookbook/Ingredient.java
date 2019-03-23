package pl.mkpk.newhorizon.cookbook;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    public int id;
    public String name;
    public String specifiedName;

    public BigDecimal calculationPer;//specyfic calculation
    //example: 1 egg can have 64g -> calculationPer = 64, unit = "sztuki" (for stacking ingredients)
    public StackType unit;

    public boolean alergen;

    //unification data: per 100g/ml only
    public BigDecimal kalories;
    public BigDecimal fat;
    public BigDecimal satturatedFat;
    public BigDecimal protein;
    public BigDecimal carbohydrates;
    public BigDecimal sugar;
    public BigDecimal salt;

    public Ingredient(){
        this.name = "Default";
        this.calculationPer = BigDecimal.valueOf(100);
        this.unit = StackType.UNIT;
        this.alergen = false;
        this.kalories = BigDecimal.valueOf(100);
    }

    public Ingredient(String name, BigDecimal calculationPer, StackType unit, boolean alergen, BigDecimal kaloriesPer100) {
        this.name = name;
        this.calculationPer = calculationPer;
        this.unit = unit;
        this.alergen = alergen;
        this.kalories = kaloriesPer100;
    }

    @Override
    public String toString() {
        if(alergen){
            return name+"(Alergen)";
        }
        return name;
    }
}
