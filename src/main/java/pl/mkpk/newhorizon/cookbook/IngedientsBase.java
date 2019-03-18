package pl.mkpk.newhorizon.cookbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngedientsBase {

    public Map<String,Ingredient> ingredientMap;

    public IngedientsBase(){
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
