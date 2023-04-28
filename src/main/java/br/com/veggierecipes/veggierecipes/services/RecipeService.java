package br.com.veggierecipes.veggierecipes.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.veggierecipes.utils.Utils;
import br.com.veggierecipes.veggierecipes.models.Comments;
import br.com.veggierecipes.veggierecipes.models.Recipe;
import br.com.veggierecipes.veggierecipes.models.User;
import br.com.veggierecipes.veggierecipes.models.enums.MealType;
import br.com.veggierecipes.veggierecipes.repositories.CommentsRepository;
import br.com.veggierecipes.veggierecipes.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    private final String path = System.getProperty("user.dir") + "/src/main/resources/static/img/recipes";

    public Recipe create(Recipe recipe, MultipartFile image) {

        if (recipe.getId() == null) {

            List<String> mappedMode = Utils.transformToList(recipe.getPreparationMode());
            recipe.setPreparationMode(mappedMode);

            List<String> mappedIngredients = Utils.transformToList(recipe.getIngredients());
            recipe.setIngredients(mappedIngredients);

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
        List<String> mappedMode = Utils.transformToList(recipe.getPreparationMode());
        recipe.setPreparationMode(mappedMode);

        List<String> mappedIngredients = Utils.transformToList(recipe.getIngredients());
        recipe.setIngredients(mappedIngredients);
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

    public void saveComment(Long id, Comments comment, User user) throws Exception {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new Exception("Recipe not found!"));

        comment.setCommentedAt(LocalDate.now());
        comment.setAuthor_image_address(user.getImage_address());
        comment.setAuthor_email(user.getEmail());
        comment.setContent(comment.getContent());
        comment.setAuthor_name(user.getName());
        recipe.getComments().add(comment);

        recipeRepository.save(recipe);
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
