package br.com.veggierecipes.veggierecipes.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER("USER"),
    ADMIN("ADMIN");

    public final String name;

    private Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
