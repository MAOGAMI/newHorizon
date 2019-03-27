package pl.mkpk.newhorizon.cookbook;

import javax.persistence.*;
import java.math.BigDecimal;

@Embeddable
public class IngredientResource {

    //@Transient
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "i_id",referencedColumnName ="i_id")
    public Ingredient ingredient;

    BigDecimal numberOfUnits;

    public IngredientResource() {
        ingredient=null;
        numberOfUnits=new BigDecimal(0);
    }

    public IngredientResource(Ingredient ingredient, BigDecimal number){
        this.ingredient=ingredient;
        this.numberOfUnits=number;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return ""+ ingredient +" "+ numberOfUnits +" "+ ingredient.unit;
    }

    public BigDecimal calculateCalories() {
        BigDecimal result;
        result=ingredient.calculationPer.multiply(ingredient.calories).multiply(numberOfUnits).divide(new BigDecimal(100*100));
        return result;
    }
}
