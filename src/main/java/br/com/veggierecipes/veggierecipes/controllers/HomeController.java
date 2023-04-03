package br.com.veggierecipes.veggierecipes.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.veggierecipes.veggierecipes.models.User;
import br.com.veggierecipes.veggierecipes.models.dtos.UserDTO;
import br.com.veggierecipes.veggierecipes.services.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(Model model,
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout) {

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
        model.addAttribute("newUser", new UserDTO());

        return "register";
    }

    @PostMapping("/user/save")
    public String saveUser(UserDTO newUser, RedirectAttributes ra, BindingResult result, Model model) {

        User user = mapper.map(newUser, User.class);
        ra.addFlashAttribute("message", "Welcome! Your account was created!\nPlease check your e-mail!");
        userService.save(user);

        return "redirect:/login";
    }
}
