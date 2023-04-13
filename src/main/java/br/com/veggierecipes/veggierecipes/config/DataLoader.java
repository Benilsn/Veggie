package br.com.veggierecipes.veggierecipes.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
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

        User admin = new User(null, "Admin", "admin@hotmail.com", encoder.encode("123"),
                new SimpleGrantedAuthority("ROLE_ADMIN"), null, true);
        User user1 = new User(null, "Joao", "joao.silva@gmail.com", encoder.encode("123"),
                new SimpleGrantedAuthority("ROLE_USER"),
                "photoless.jpg", true);

        userRepository.saveAll(List.of(admin, user1));
    }

}
