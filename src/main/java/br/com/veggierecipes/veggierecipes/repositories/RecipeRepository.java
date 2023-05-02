package br.com.veggierecipes.veggierecipes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.veggierecipes.veggierecipes.models.Recipe;
import br.com.veggierecipes.veggierecipes.models.enums.MealType;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    public List<Recipe> findByType(MealType type);

    public List<Recipe> findByNameContains(String name);

    public List<Recipe> findAllByNameContainingIgnoreCase(String name);

    public List<Recipe> findAllByDescriptionContainingIgnoreCase(String name);
}
