package pl.mkpk.newhorizon.cookbook;

import java.math.BigDecimal;

public class IngredientResource {
    public Ingredient ingredient;
    BigDecimal numberOfUnits;

    public IngredientResource(Ingredient ingredient, BigDecimal number){
        this.ingredient=ingredient;
        this.numberOfUnits=number;
    }

    @Override
    public String toString() {
        return ""+ ingredient +" "+ numberOfUnits +" "+ ingredient.unit;
    }

    public BigDecimal calculateCalories() {
        BigDecimal result;
        result=ingredient.calculationPer.multiply(ingredient.kalories).multiply(numberOfUnits).divide(new BigDecimal(100*100));
        return result;
    }
}
