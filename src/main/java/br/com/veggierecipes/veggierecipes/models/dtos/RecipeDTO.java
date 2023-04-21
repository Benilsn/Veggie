package br.com.veggierecipes.veggierecipes.models.dtos;

import java.util.List;
import br.com.veggierecipes.veggierecipes.models.Comments;
import br.com.veggierecipes.veggierecipes.models.Rating;
import br.com.veggierecipes.veggierecipes.models.enums.MealType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {

    private Long id;

    @NotEmpty(message = "Name: Recipe name cannot be empty!")
    private String name;

    @Min(value = 1, message = "Preparation time: Should be greater than 1!")
    private Integer preparationTime;

    @NotEmpty(message = "Prepararion Mode: Please, insert the preparation mode!")
    private String preparationMode;

    @NotEmpty(message = "Description: Please, insert the description, Ex: ingredients, amount...")
    private String description;
    private MealType type;
    private String image_address;
    private List<Comments> comments;
    private List<Rating> rating;
}
