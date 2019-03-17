package pl.mkpk.newhorizon.cookbook;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    int id;

    @NotEmpty(message =
            "The name of the recipe can not be empty!\n" +
                    "Type recipe name.")
    @Column(name = "recipe_name")
    String recipeName;

    @Column(name = "ingredients")
    @NotEmpty(message = "Ingredient can not be empty!\n" +
            "Type ingredient!")
    String ingredients;

    String preparation;
    String allergens;

    @Column(name = "kcal_value")
    @NotEmpty
    int kcal;
}


