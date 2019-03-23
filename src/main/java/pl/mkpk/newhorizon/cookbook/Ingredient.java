package pl.mkpk.newhorizon.cookbook;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int i_id;
    @Column(/*unique=true,*/ nullable=false,length = 50)
    public String name;
    public String specifiedName;//

    @Column(nullable=false)
    public BigDecimal calculationPer;//specific calculation
    //example: 1 egg can have 64g -> calculationPer = 64, unit = "sztuki" (for stacking ingredients)
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    public StackType unit;

    @Column(nullable=false)
    public boolean alergen;

    //unification data: per 100g/ml only
    @Column(nullable=false)
    public BigDecimal calories;
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
        this.calories = BigDecimal.valueOf(100);
    }

    public Ingredient(String name, BigDecimal calculationPer, StackType unit, boolean alergen, BigDecimal kaloriesPer100) {
        this.name = name;
        this.calculationPer = calculationPer;
        this.unit = unit;
        this.alergen = alergen;
        this.calories = kaloriesPer100;
    }

    public int getI_id() {
        return i_id;
    }

    public void setI_id(int i_id) {
        this.i_id = i_id;
    }

    @Override
    public String toString() {
        if(alergen){
            return name+"(Alergen)";
        }
        return name;
    }
}
