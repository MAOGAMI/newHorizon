package pl.mkpk.newhorizon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkpk.newhorizon.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Recipe findByRecipeName(String recipeName);
}
