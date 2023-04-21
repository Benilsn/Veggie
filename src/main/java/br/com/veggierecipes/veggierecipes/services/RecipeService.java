package br.com.veggierecipes.veggierecipes.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.veggierecipes.veggierecipes.models.Recipe;
import br.com.veggierecipes.veggierecipes.models.enums.MealType;
import br.com.veggierecipes.veggierecipes.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    private final String path = System.getProperty("user.dir") + "/src/main/resources/static/img/recipes";

    public Recipe create(Recipe recipe, MultipartFile image) {

        if (recipe.getId() == null) {
            if (image.getSize() > 0) {
                saveImage(image);
                recipe.setImage_address(image.getOriginalFilename());
            } else {
                recipe.setImage_address("default_recipe_image.jpg");
            }
        } else {
            if (image.getSize() > 0) {
                saveImage(image);
                recipe.setImage_address(image.getOriginalFilename());
            } else {
                recipe.setImage_address(recipe.getImage_address());
            }
        }

        return recipeRepository.save(recipe);
    }

    public Recipe create(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void saveImage(MultipartFile image) {
        try {
            Files.copy(image.getInputStream(), Paths.get(path + File.separator + image.getOriginalFilename()));
        } catch (IOException e) {

        }
    }

    public List<Recipe> getAll() {
        return recipeRepository.findAll();
    }

    public Recipe getById(Long id) throws Exception {
        return recipeRepository.findById(id).orElseThrow(() -> new Exception("Recipe not found!"));
    }

    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> getByType(MealType type) {
        return recipeRepository.findByType(type);
    }

    // public List<Recipe> searchRecipe(String name) {
    // return repository.findByNameContains(name);
    // }

    // public List<Recipe> getByNameContains(String name) {

    // var findByName = repository.findAllByNameContainingIgnoreCase(name);
    // var findByDescription =
    // repository.findAllByDescriptionContainingIgnoreCase(name);

    // var list = Stream.concat(findByName.stream(),
    // findByDescription.stream()).distinct()
    // .collect(Collectors.toList());

    // return list;
    // }
}
