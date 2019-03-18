package pl.mkpk.newhorizon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkpk.newhorizon.cookbook.CookingRecipe;

@Repository
public interface CookingRecipeRepository extends JpaRepository<CookingRecipe, Integer> {
}
