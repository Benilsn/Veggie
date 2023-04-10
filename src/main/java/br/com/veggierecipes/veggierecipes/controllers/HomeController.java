package br.com.veggierecipes.veggierecipes.controllers;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.veggierecipes.exception.EmailAlreadyRegisteredException;
import br.com.veggierecipes.veggierecipes.models.User;
import br.com.veggierecipes.veggierecipes.models.dtos.UserDTO;
import br.com.veggierecipes.veggierecipes.services.UserService;
import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "Home | Veggie");
        return "index";
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
}
