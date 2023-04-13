package br.com.veggierecipes.veggierecipes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.veggierecipes.veggierecipes.models.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
