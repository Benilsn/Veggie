package br.com.veggierecipes.veggierecipes.controllers;

import java.util.Collections;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.veggierecipes.exception.EmailAlreadyRegisteredException;
import br.com.veggierecipes.veggierecipes.models.Comments;
import br.com.veggierecipes.veggierecipes.models.Rating;
import br.com.veggierecipes.veggierecipes.models.User;
import br.com.veggierecipes.veggierecipes.models.dtos.RecipeDTO;
import br.com.veggierecipes.veggierecipes.models.dtos.UserDTO;
import br.com.veggierecipes.veggierecipes.models.enums.MealType;
import br.com.veggierecipes.veggierecipes.services.RecipeService;
import br.com.veggierecipes.veggierecipes.services.UserService;
import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Home | Veggie");

        var initialPageRecipes = recipeService.getAll()
                .stream()
                .map(r -> mapper.map(r, RecipeDTO.class))
                .limit(11)
                .collect(Collectors.toList());

        Collections.shuffle(initialPageRecipes);
        model.addAttribute("initialPageRecipes", initialPageRecipes);

        return "index";
    }

    @GetMapping("/recipes/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {

        try {
            var recipe = recipeService.getById(id);
            double recipeAverageRating = 0;
            int rateAmount = recipe.getRating().size();

            if (recipe.getRating().size() != 0) {
                recipeAverageRating = recipe.getRating().stream()
                        .map(r -> {
                            return r.getRated_value();
                        })
                        .collect(Collectors.toList())
                        .stream().reduce(0, (a, b) -> a + b).doubleValue() / rateAmount;
            }

            recipeAverageRating = Math.round(recipeAverageRating * 100.0) / 100.0;

            model.addAttribute("pageTitle", recipe.getName() + " | Veggie");
            model.addAttribute("recipe", recipe);
            model.addAttribute("comment", new Comments());
            model.addAttribute("rating", new Rating());
            model.addAttribute("recipeAverageRating", recipeAverageRating);
            model.addAttribute("rateAmount", rateAmount);

        } catch (Exception e) {

        }

        return "recipe-by-id";
    }

    @PostMapping("/recipes/{id}")
    public String saveCommentAndRating(@PathVariable("id") Long id, @ModelAttribute("comment") Comments comment,
            @ModelAttribute("rating") Rating rating)
            throws Exception {

        var loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.getByName(loggedUser);

        recipeService.saveCommentAndRating(id, comment, rating, user);
        return "redirect:/recipes/" + id;
    }

    @GetMapping("/login")
    public String loginPage(Model model,
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout) {

        model.addAttribute("pageTitle", "Login | Veggie");
        if (error != null) {
            model.addAttribute("error", "Invalid Credentials!");
        }

        if (logout != null) {
            model.addAttribute("logout", "You've been logged out successfully.");
        }

        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("pageTitle", "Register | Veggie");
        model.addAttribute("newUser", new UserDTO());

        return "register";
    }

    @PostMapping("/user/save")
    public String saveUser(@Valid UserDTO newUser, BindingResult result, Model model, RedirectAttributes ra) {

        if (result.hasErrors()) {

            ra.addFlashAttribute("errors",
                    result.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList()));
            return "redirect:/register";
        }

        try {
            User user = mapper.map(newUser, User.class);
            ra.addFlashAttribute("message", "Welcome! Your account was created!\nPlease check your e-mail!");
            userService.save(user);

        } catch (EmailAlreadyRegisteredException e) {
            ra.addFlashAttribute("emailTaken", "E-mail already taken!");
            return "redirect:/register";
        }

        return "redirect:/login";
    }

    @GetMapping("/recipes/types/{type}")
    public String getByType(@PathVariable("type") MealType type, Model model) {

        var listOfRecipes = recipeService.getByType(type);

        model.addAttribute("pageTitle", type.getName() + " | Veggie");
        model.addAttribute("listOfRecipes", listOfRecipes);

        return "find-by-type";
    }

    @GetMapping("/{recipe}")
    public String searchRecipe(@RequestParam(name = "recipe") String name, Model model) {
        model.addAttribute("pageTitle", "Search | Veggie");

        var listOfRecipes = recipeService.getByNameContains(name);

        model.addAttribute("listOfRecipes", listOfRecipes);

        return "search-recipe";
    }

    @GetMapping("/profile")
    public String profile(Model model) throws Exception {
        model.addAttribute("pageTitle", "My Profile | Veggie");

        var user = mapper.map(
                userService.getByName(SecurityContextHolder.getContext().getAuthentication().getName()),
                UserDTO.class);

        model.addAttribute("user", user);
        return "profile";
    }
}
