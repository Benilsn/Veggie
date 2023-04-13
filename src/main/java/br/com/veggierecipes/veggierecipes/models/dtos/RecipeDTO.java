package br.com.veggierecipes.veggierecipes.models.dtos;

import java.util.List;

import br.com.veggierecipes.veggierecipes.models.Comments;
import br.com.veggierecipes.veggierecipes.models.Rating;
import br.com.veggierecipes.veggierecipes.models.enums.MealType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {

    private Long id;
    private String name;
    private Integer preparationTime;
    private String preparationMode;
    private String description;
    private MealType type;
    private String image_address;
    private List<Comments> comments;
    private List<Rating> rating;
}
