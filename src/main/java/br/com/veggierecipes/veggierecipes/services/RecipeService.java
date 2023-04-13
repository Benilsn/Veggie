package br.com.veggierecipes.veggierecipes.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.veggierecipes.veggierecipes.models.Recipe;
import br.com.veggierecipes.veggierecipes.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    private final String path = System.getProperty("user.dir") + "/src/main/resources/static/recipes";

    public Recipe create(Recipe recipe, MultipartFile image) {
        if (image != null) {
            saveImage(image);
        }
        recipe.setImage_address(image.getOriginalFilename());
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
}
