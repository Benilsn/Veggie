package br.com.veggierecipes.veggierecipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.veggierecipes.veggierecipes.models.User;
import br.com.veggierecipes.veggierecipes.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    private PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = repository.findByEmail(email).orElseThrow(() -> new Exception("User not found!"));

            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(User user) {
        user.setRole(new SimpleGrantedAuthority("USER"));
        user.setIsEnabled(true);
        user.setImage_address("photoless.jpg");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

}
