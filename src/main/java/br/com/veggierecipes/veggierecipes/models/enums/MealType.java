package br.com.veggierecipes.veggierecipes.models.enums;

public enum MealType {

    BREAD_AND_CAKES("Bread and Cakes"),
    VEGETABLES("Vegetables"),
    SNACKS("Snacks"),
    DRINKS("Drinks"),
    DESSERTS("Desserts"),
    PASTA("Pasta"),
    SALADS("Salads"),
    OTHER("Others"),
    SAUCES_AND_ACCOMPANIMENTS("Sauces and Accompaniments");

    private String name;

    MealType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}