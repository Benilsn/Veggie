package br.com.veggierecipes.veggierecipes.controllers;

import java.util.HashMap;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.veggierecipes.veggierecipes.models.Recipe;
import br.com.veggierecipes.veggierecipes.models.dtos.RecipeDTO;
import br.com.veggierecipes.veggierecipes.services.RecipeService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/recipes")
public class AdminController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/create")
    public String home(Model model) {
        model.addAttribute("pageTitle", "New Recipe | Veggie");

        model.addAttribute("newRecipe", new RecipeDTO());
        return "admin/create-recipe";
    }

    @PostMapping("/save")
    public String saveRecipe(
            @Valid RecipeDTO recipe,
            BindingResult result,
            RedirectAttributes ra,
            @PathVariable(name = "image", required = false) MultipartFile image) {

        if (result.hasErrors()) {
            ra.addFlashAttribute("errors",
                    result.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
            return "redirect:/admin/recipes/create";

        } else {
            if (recipe.getId() == null) {
                ra.addFlashAttribute("message", "Recipe Successfully created!");

                recipeService.create(mapper.map(recipe, Recipe.class), image);

            } else {
                // var recipeToUpdate = recipeService.getById(recipe.getId());

                // recipeToUpdate.setName(recipe.getName());
                // recipeToUpdate.setPreparationTime(recipe.getPreparationTime());
                // recipeToUpdate.setMealType(recipe.getMealType());
                // recipeToUpdate.setPreparationMode(recipe.getPreparationMode());
                // recipeToUpdate.setDescription(recipe.getDescription());
                // recipeToUpdate.setIngredients(recipe.getIngredients());
                // if (image != null) {
                // recipeToUpdate.setImage(image.getOriginalFilename());
                // }

                // recipeService.create(recipeToUpdate, image);

                // ra.addFlashAttribute("message", "Recipe Successfully updated!");
                // return "redirect:/auth/admin/recipes/show";
            }
        }
        return "redirect:/admin/recipes/create";
    }
}
