package br.com.veggierecipes.veggierecipes.models;

import java.util.List;
import java.util.Map;

import br.com.veggierecipes.veggierecipes.models.enums.MealType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "recipes")
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipe_name")
    private String name;

    @Column(name = "preparation_time")
    private Integer preparationTime;

    @ElementCollection(fetch = FetchType.LAZY)
    @Column(length = 1000)
    private List<String> preparationMode;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "type")
    private MealType type;

    @Column(name = "image_address")
    private String image_address;

    @ElementCollection
    private List<String> ingredients;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Comments.class)
    @Column(name = "comments")
    private List<Comments> comments;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Rating.class)
    @Column(name = "rating")
    private List<Rating> rating;

    public Recipe(String name, Integer preparationTime, List<String> preparationMode, String description, MealType type,
            String image_address, List<String> ingredients) {
        this.name = name;
        this.preparationTime = preparationTime;
        this.preparationMode = preparationMode;
        this.description = description;
        this.type = type;
        this.image_address = image_address;
        this.ingredients = ingredients;
    }
}
