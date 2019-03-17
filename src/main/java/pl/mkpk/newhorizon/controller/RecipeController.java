package pl.mkpk.newhorizon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.mkpk.newhorizon.model.Recipe;
import pl.mkpk.newhorizon.service.RecipeService;

@Controller
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchRecipe(Recipe recipe){
        ModelAndView modelAndView = new ModelAndView();
        Recipe existingRecipe = recipeService.findByRecipeName(recipe.getRecipeName());
        modelAndView.addObject("recipe", existingRecipe);
        return modelAndView;
    }
}
