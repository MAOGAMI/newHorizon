package pl.mkpk.newhorizon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mkpk.newhorizon.cookbook.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
