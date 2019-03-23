package pl.mkpk.newhorizon.cookbook;

import java.util.HashMap;
import java.util.Map;

public class IngredientsBase {

    public Map<String,Ingredient> ingredientMap;

    public IngredientsBase(){
        ingredientMap=new HashMap<>();
    }

    public void addIngredient(Ingredient ingredient){
        ingredientMap.put(ingredient.name,ingredient);
    }

    public Ingredient getIngredient(String name){
        Ingredient result = ingredientMap.get(name);
        if(result==null){
            throw new RuntimeException("no ingredient find");
        }
        return result;
    }
}
