package br.com.veggierecipes.veggierecipes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.veggierecipes.veggierecipes.models.Role;
import br.com.veggierecipes.veggierecipes.models.User;
import br.com.veggierecipes.veggierecipes.repositories.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        User admin = new User(null, "Admin", "admin@hotmail.com", encoder.encode("123"), Role.ADMIN, null, null);

        userRepository.save(admin);
    }

}
