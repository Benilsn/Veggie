package br.com.veggierecipes.veggierecipes.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.veggierecipes.veggierecipes.models.Recipe;
import br.com.veggierecipes.veggierecipes.models.User;
import br.com.veggierecipes.veggierecipes.models.enums.MealType;
import br.com.veggierecipes.veggierecipes.repositories.RecipeRepository;
import br.com.veggierecipes.veggierecipes.repositories.UserRepository;
import br.com.veggierecipes.veggierecipes.services.RecipeService;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        createUsers();
        createRecipes();
    }

    void createUsers() {
        User admin = new User(null, "Admin", "admin@hotmail.com", encoder.encode("123"),
                new SimpleGrantedAuthority("ROLE_ADMIN"), null, true);
        User user1 = new User(null, "Joao", "joao.silva@gmail.com", encoder.encode("123"),
                new SimpleGrantedAuthority("ROLE_USER"),
                "photoless.jpg", true);

        userRepository.saveAll(List.of(admin, user1));
    }

    void createRecipes() {
        Recipe r1 = new Recipe("Spaghetti Sauce", 15,
                List.of("(1) Peel the tomatoes\n(2) Cook the Onion and Garlic\n(3) Boil the Sauce"),
                "This is the most beautiful tasting low-fat pasta sauce you'll ever taste! Don't wait to try it!",
                MealType.SAUCES_AND_ACCOMPANIMENTS, "spaghetti-sauce.jpg",
                List.of("Onion\nTomato\nOregano\nSalt\nSugar\nGarlic\nBasil"));

        recipeService.create(r1);

        Recipe r2 = new Recipe("Vegan Cuscous", 30,
                List.of("(1) Boil the water, hydrate the soy protein for 10 minutes\n" +
                        "(2) Prepare cuscous\n" +
                        "(3) In a pot, put Olive oil, Onion, Garlic, chilis and chives\n" +
                        "(4) Add the soy protein\n" +
                        "(5) Let it rest for 20 minutes\n" +
                        "(6) Put in a container of your preference, add the pot content, pea, olive, parsley and salt."),
                "Made from corn, couscous is able to turn into a nutritious, tasty and" +
                        "colorful preparation, which can appear even in the diet of those who do not eat meat.",
                MealType.OTHER, "cuscuz-vegano.jpg",
                List.of("Corn Flour\nSoy Protein\nWater\nPea\nOlive\nRed Chilli\nGreen Chilli"));

        recipeService.create(r2);

        // Ingredient i43 = new Ingredient("Lettuce", FoodType.OTHER);
        // Ingredient i44 = new Ingredient("Red Onion", FoodType.VEGETABLE);
        // Ingredient i45 = new Ingredient("Liquid Smoke", FoodType.OTHER);
        // Ingredient i46 = new Ingredient("Pickle", FoodType.VEGETABLE);
        // Ingredient i47 = new Ingredient("Vegan Mayonnaise", FoodType.OTHER);
        // Ingredient i48 = new Ingredient("Bun", FoodType.OTHER);

        Recipe r3 = new Recipe("Vegan X-Burguer", 35,
                List.of("(1) Mix ground protein with liquid smoke\n" +
                        "(2) When shaping the patty, make sure to press it hard so it stays together" +
                        "when cooking\n" + "(3) Cook ground protein patty on a pan or in an oven\n" +
                        "(4)Assemble burger by placing the patty, pickles, onions, tomatoes, and" +
                        "lettuce in between two buns.\n"),
                "With vegan protein, you can make this delicious Vegan Impossible Whopper" +
                        "Copycat Recipe! You only need a handful of ingredients.",
                MealType.OTHER, "veggie-burguer.jpg",
                List.of("Lettuce\nRed Onion\nLiquid Smoke\nPickle\nVegan Mayonnaise\nBun"));

        recipeService.create(r3);

        // Ingredient i49 = new Ingredient("Almond Milk", FoodType.OTHER);
        // Ingredient i50 = new Ingredient("Chocolate Chips",
        // FoodType.SUGAR_AND_SUGAR_PRODUCTS);
        // Ingredient i51 = new Ingredient("Coconut Sugar",
        // FoodType.SUGAR_AND_SUGAR_PRODUCTS);
        // Ingredient i52 = new Ingredient("Cocoa Powder", FoodType.OTHER);
        // Ingredient i53 = new Ingredient("Vanilla extract",
        // FoodType.SUGAR_AND_SUGAR_PRODUCTS);

        Recipe r4 = new Recipe("Vegan Hot Chocolate", 20,
                List.of("(1) Add every ingredient except vanilla into a saucepan. Stir\n" +
                        "(2) Cook over medium heat on a stove. Stir often\n" +
                        "(3) Remove saucepan from the stove\n" +
                        "(4) Add vanilla and mix\n" +
                        "(5) Pour into a cup" +
                        "(6)Enjoy! "),
                "This Vegan Hot Chocolate is pure comfort food on a cold winter day. Easy and" +
                        "quick to make so you can enjoy it in no time!",
                MealType.DESSERTS, "vegan-hot-chocolate.jpg",
                List.of("Almond Milk\nChocolate Chips\nCoconut Sugar\nCocoa Powder\nVanilla extract\nSalt"));

        recipeService.create(r4);

        // Ingredient i54 = new Ingredient("Macaroni", FoodType.OTHER);
        // Ingredient i55 = new Ingredient("Vegan Alfredo Sauce", FoodType.OTHER);
        // Ingredient i56 = new Ingredient("Nutricional Yeast", FoodType.OTHER);

        Recipe r5 = new Recipe("Vegan Mac & Cheese", 15,
                List.of("(1) Cook macaroni noodles according to instructions on the box\n" +
                        "(2) Drain\n" +
                        "(3) Pour in alfredo sauce and stir until combined\n" +
                        "(4) Add nutritional yeast and stir\n" +
                        "(5) Serve and enjoy!"),
                "Creamy, cheesy, and delicious, this Vegan Mac & Cheese is the perfect" +
                        "comfort food for any day! Veggie sauce makes this completely dairy-free!",
                MealType.PASTA, "vegan-mac-in-cheese.jpg",
                List.of("Macaroni\nVegan Alfredo Sauce Chips\nNutricional Yeast"));

        recipeService.create(r5);

        Recipe r6 = new Recipe("Roasted Sweet Potatoes", 50,
                List.of("(1) Preheat oven;\n" +
                        "(2) Peel and chop sweet potatoes. Place in a large mixing bowl\n" +
                        "(3) Cover with oil and seasoning and mix well\n" +
                        "(4) Place sweet potatoes spaced out on a pan\n" +
                        "(5) Cook for 35 minutes" +
                        "(6) Wait until completely cool before serving" +
                        "(7) Enjoy!"),
                "After Halloween, the holiday season will finally be here! Which means I need" +
                        "to begin preparing my dishes. Mashed potatoes and gravy have already been" +
                        "perfected, and I have more ideas on the way. You can never have too many side" +
                        "dishes though, so I decided on these roasted sweet potatoes! They’re soft," +
                        "flavorful, and so delicious! And super simple too!",
                MealType.SNACKS, "Roasted-Sweet-Potatoes.jpg",
                List.of("Sweet Potato\nPaprika\nCumin\nSalt\nGarlic\nBlack Pepper\nOlive Oil"));

        recipeService.create(r6);

        Recipe r7 = new Recipe("Vegan Pumpkin Pancakes", 20,
                List.of("(1) Mix dry ingredients together in a large bowl\n" +
                        "(2) Add wet ingredients until well combined. Don’t over mix\n" +
                        "(3) Place a pan over medium heat and add a bit of batter to the pan\n"
                        +
                        "(4) After 1-2 minutes, flip, and cook for another 1-2 minutes\n" +
                        "(5) Place on a plate after finished" +
                        "(6) Repeat" +
                        "(7) Serve with toppings and enjoy!"),
                "Pumpkin spice latte, pumpkin spice, pumpkin muffins, and now pumpkin" +
                        "pancakes. Maybe I’m a bit too obsessed with pumpkin. But, hey, it’s Fall!" +
                        "These pancakes are so delicious! They’re fluffy and the pumpkin flavor is" +
                        "very apparent. I served them with maple syrup and chopped pecans and my" +
                        "family loved it!",
                MealType.SNACKS, "pumpkin-pancakes.jpg",
                List.of("Flour\nBaking Powder\nBaking Soda\nBrown Sugar\nPumpkin Pie Spice\nPumpkin Puree\nSalt\nAlmond Milk\nVanilla extract\nOlive Oil"));

        recipeService.create(r7);

        // Ingredient i66 = new Ingredient("Bread", FoodType.OTHER);
        // Ingredient i67 = new Ingredient("Celery", FoodType.VEGETABLE);
        // Ingredient i68 = new Ingredient("Fresh Herbs", FoodType.SPICES_AND_HERBS);

        // ingredientRepository.saveAll(List.of(i66, i67, i68));

        // Recipe r8 = new Recipe("Vegan Stuffing", 30,
        // "(1) Cut bread into small squares;\n" +
        // "(2) Toast in the oven;\n" +
        // "(3) Chop celery and onions;\n" +
        // "(4) Pour oil into a pan over medium-high heat;\n" +
        // "(5) Sauté garlic, onions, and celery until onions are translucent;" +
        // "(6) In a large bowl, add toasted bread, sautéd veggies, and seasoning. Toss
        // until mixed;"
        // +
        // "(7) Place in a pan and spread evenly;" +
        // "(8) Pour vegetable stock over;" +
        // "(9) Cover pan with aluminum foil;" +
        // "(10) Bake in the oven for 25 minutes. Afterward, take off foil and cook for
        // another 10 minutes;"
        // +
        // "(11) Let cool;" +
        // "(12) Enjoy!",
        // "Nothing says holiday meals like stuffing. This Vegan Stuffing is the perfect
        // no-dairy version of the classic favorite!\nThis stuffing is 100% dairy-free,
        // soft with a crispy top, and lots of flavor! Plus, it’s easy and barely takes
        // any effort!",
        // MealType.SNACKS,
        // List.of(i66, i67, i68, i2, i29, i32),
        // "vegan-stuffing.jpg");

        // recipeRepository.save(r8);

        // Ingredient i69 = new Ingredient("Cauliflower", FoodType.VEGETABLE);
        // ingredientRepository.saveAll(List.of(i69));

        // Recipe r9 = new Recipe("Roasted Cauliflower", 40,
        // "(1) Wash and cut cauliflower into similar-sized pieces;\n" +
        // "(2) Place cauliflower in a bowl and cover with oil and seasoning;\n" +
        // "(3) Toss until well covered;\n" +
        // "(4) Spread cauliflower over a baking sheet lined with parchment paper;\n"
        // +
        // "(5) Cook for 30-35 minutes or until tender and browning;" +
        // "(6) Wait until cool;" + "(7) Enjoy!",

        // "Healthy, flavorful, and delicious, this Roasted Cauliflower is a perfect
        // side dish to your dinners. This recipe is sure to leave you wanting more!\n",
        // MealType.VEGETABLES,
        // List.of(i69, i32, i29, i5, i32),
        // "roasted-cauliflower.jpg");

        // recipeRepository.save(r9);

        // Ingredient i70 = new Ingredient("Maple Syrup", FoodType.VEGETABLE);
        // ingredientRepository.saveAll(List.of(i70));

        // Recipe r10 = new Recipe("Pumpkin Spice Latte", 10,
        // "(1) Wash and cut cauliflower into similar-sized pieces;\n" +
        // "(2) Place cauliflower in a bowl and cover with oil and seasoning;\n" +
        // "(3) Toss until well covered;\n" +
        // "(4) Spread cauliflower over a baking sheet lined with parchment paper;\n"
        // +
        // "(5) Cook for 30-35 minutes or until tender and browning;" +
        // "(6) Wait until cool;" + "(7) Enjoy!",

        // "This Vegan Pumpkin Spice Latte is a dairy-free twist on a fall favorite.
        // Flavorful, and delicious, this drink is much better than store-bought
        // equivalents.\nAs soon as it turns fall, I will be having this drink almost
        // week. It’s warm, comforting, and the perfect thing to have as you cozy up in
        // your favorite sweater and read a book. My favorite about thing about this
        // drink is how easy it is!",
        // MealType.VEGETABLES,
        // List.of(i70, i64, i65, i26, i53, i49),
        // "vegan-pumpkin-spice-latte.jpg");

        // recipeRepository.save(r10);

        // Ingredient i71 = new Ingredient("Vegetable stock", FoodType.VEGETABLE);
        // Ingredient i72 = new Ingredient("Thyme", FoodType.SPICES_AND_HERBS);
        // Ingredient i73 = new Ingredient("Soy sauce", FoodType.SPICES_AND_HERBS);
        // ingredientRepository.saveAll(List.of(i71, i72, i73));

        // Recipe r11 = new Recipe("Vegan Gravy", 10,
        // "(1) Mix all ingredients in a large pot.;\n" +
        // "(2) Place pot on the stove over medium heat.;\n" +
        // "(3) Stir when bubbles are visible until the sauce becomes thicker.;\n"
        // +
        // "(4) Let cool to thicken further.;\n"
        // +
        // "(5) Serve over mashed potatoes.;",

        // "This Vegan Gravy is made with pantry ingredients and cooks in five minutes.
        // It’s smooth, flavorful, and absolutely delicious! Perfect addition to your
        // holiday meals!\nThis gravy is full of flavor, creamy, and the perfect sauce
        // to serve on your holiday table.",
        // MealType.VEGETABLES,
        // List.of(i71, i72, i73, i60, i2, i32, i56, i5, i9),
        // "vegan-gravy.jpg");

        // recipeRepository.save(r11);

        // Ingredient i74 = new Ingredient("Russet Potato", FoodType.VEGETABLE);
        // ingredientRepository.saveAll(List.of(i74));

        // Recipe r12 = new Recipe("Vegan Mashed Potatoes", 30,
        // "(1) Mix all ingredients in a large pot.;\n" +
        // "(2) Place pot on the stove over medium heat.;\n" +
        // "(3) Stir when bubbles are visible until the sauce becomes thicker.;\n"
        // +
        // "(4) Let cool to thicken further.;\n"
        // +
        // "(5) Serve over mashed potatoes.;",

        // "These Vegan Mashed Potatoes are a dairy-free version of the classic side
        // dish. Creamy, fluffy, and super easy, made with pantry ingredients, no vegan
        // butter needed!\nMy favorite part about these mashed potatoes is that they’re
        // creamy, smooth, fluffy, and so easy to make!",
        // MealType.OTHER,
        // List.of(i74, i29, i32, i5, i9, i29, i49),
        // "mashed-potatoes.jpg");

        // recipeRepository.save(r12);

        // Ingredient i75 = new Ingredient("Lime", FoodType.VEGETABLE);
        // ingredientRepository.saveAll(List.of(i75));

        // Recipe r13 = new Recipe("Pico de Gallo", 30,
        // "(1) Dice the tomatoes, pepper, and onion into small pieces.;\n" +
        // "(2) Place in a large bowl.;\n" +
        // "(3) Place in a large bowl.;\n"
        // +
        // "(4) Serve with chips or in a taco.;\n"
        // +
        // "(5) Enjoy!;",

        // "Made with plump and juicy tomatoes, fresh pepper and onion, this Pico de
        // Gallo is delicious served with tortilla chips or in vegan tacos!\nEvery
        // Tuesday, we make tacos. And this Pico de Gallo is a new addition! It’s so
        // easy to make and perfect as a topping. This recipe is quick and easy,
        // requiring only a few ingredients.",
        // MealType.OTHER,
        // List.of(i75, i9, i5, i31, i41, i2),
        // "pico-de-gallo.jpg");

        // recipeRepository.save(r13);

        // Ingredient i76 = new Ingredient("Yellow Cornmeal",
        // FoodType.CEREALS_AND_PULSES);
        // ingredientRepository.saveAll(List.of(i76));

        // Recipe r14 = new Recipe("Vegan Cornbread", 35,
        // "(1) Mix all dry ingredients together in a bowl until combined.;\n" +
        // "(2) Add wet ingredients and mix.;\n" +
        // "(3) Pour into a lightly greased skillet or round cake pan.;\n"
        // +
        // "(4) Bake at 400 for 23-25 minutes or until a toothpick comes out clean.;\n"
        // +
        // "(5) Enjoy!;",

        // "This Vegan Cornbread is fluffy, moist, and flavorful. Requiring only a few
        // ingredients, you can have a delicious side dish in no time!",
        // MealType.BREAD_AND_CAKES,
        // List.of(i76, i60, i61, i18, i25, i23),
        // "vegan-cornbread.jpg");

        // recipeRepository.save(r14);

        // Ingredient i77 = new Ingredient("Spinach", FoodType.CEREALS_AND_PULSES);
        // Ingredient i78 = new Ingredient("Banana", FoodType.FRUITS);
        // Ingredient i79 = new Ingredient("Ginger", FoodType.OTHER);
        // ingredientRepository.saveAll(List.of(i77, i78, i79));

        // Recipe r15 = new Recipe("Vegan Breakfast Smoothie", 35,
        // "(1) Place all ingredients into a blender.;\n" +
        // "(2) Blend until smooth.;\n" +
        // "(5) Enjoy!;",

        // "Creamy, energizing, and super healthy, this Vegan Breakfast Smoothie is a
        // perfect and easy drink to make when you don’t have a lot of time. Simply add
        // all ingredients to the blender and blend!",
        // MealType.SAUCES_AND_ACCOMPANIMENTS,
        // List.of(i77, i78, i79, i15, i23),
        // "vegan-breakfast-smoothie.jpg");

        // recipeRepository.save(r15);

        // Ingredient i80 = new Ingredient("Chickpeas", FoodType.OTHER);
        // Ingredient i81 = new Ingredient("Nut Butter", FoodType.OTHER);
        // Ingredient i82 = new Ingredient("Almond Extract", FoodType.OTHER);
        // ingredientRepository.saveAll(List.of(i80, i81, i82));

        // Recipe r16 = new Recipe("Vegan Edible Cookie Dough", 30,
        // "(1) In a food processor, blend the chickpeas until smooth.;\n" +
        // "(2) Add oats, maple syrup, peanut butter, and almond extract and blend.;\n"
        // +
        // "(3) Transfer to a bowl and add mix-ins. Mix until combined;\n"
        // +
        // "(4) Serve in a bowl and enjoy!;\n",
        // "Smooth, sweet, and creamy, this Vegan Edible Cookie Dough is an amazing and
        // easy dessert or snack! Simply serve in a bowl and enjoy!",
        // MealType.DESSERTS,
        // List.of(i80, i81, i82, i10, i70, i50),
        // "Vegan-Edible-Cookie-Dough.jpg");

        // recipeRepository.save(r16);

        // Ingredient i83 = new Ingredient("Avocado", FoodType.FRUITS);
        // Ingredient i84 = new Ingredient("Tahini", FoodType.OTHER);
        // Ingredient i85 = new Ingredient("Garlic Cloves", FoodType.FRUITS);
        // Ingredient i86 = new Ingredient("Lemon Juice ", FoodType.FRUITS);
        // ingredientRepository.saveAll(List.of(i83, i84, i85, i86));

        // Recipe r17 = new Recipe("Vegan Avocado Hummus", 5,
        // "(1) Add all ingredients in a food processor or blender. Blend until
        // smooth.;\n" +
        // "(2) Add more water if needed.;\n"
        // +
        // "(3) Serve with chips, crackers, or carrots.;\n"
        // +
        // "(4) Enjoy!\n",
        // "This Vegan Avocado Hummus is creamy, light, and absolutely delicious! Using
        // only a few ingredients, this dip is like guacamole and hummus combined—a
        // win-win for everyone!",
        // MealType.SAUCES_AND_ACCOMPANIMENTS,
        // List.of(i83, i84, i85, i86, i80, i29, i5, i59),
        // "avocadohummus.jpg");

        // recipeRepository.save(r17);

        // Ingredient i87 = new Ingredient("Ice Cubes ", FoodType.OTHER);
        // Ingredient i88 = new Ingredient("Instant Coffe Mix ", FoodType.OTHER);
        // Ingredient i89 = new Ingredient("Medjool Dates", FoodType.OTHER);
        // ingredientRepository.saveAll(List.of(i87, i88, i89));

        // Recipe r18 = new Recipe("Vegan Iced Cappuccino ", 365,
        // "(1) Freeze the banana overnight.;\n" +
        // "(2) Stir the instant coffee mix and cold water together to make cold
        // coffee.;\n"
        // +
        // "(3) Blend together the frozen bananas, ice cubes, cold coffee, plant-based
        // milk, and dates.;\n"
        // +
        // "(4) Serve and enjoy! \n",
        // "This flavorful Vegan Iced Cappuccino is the ultimate drink for hot days!
        // Using cold coffee, bananas, your favorite plant-based milk, and Medjool
        // dates, this recipe is one you can make in no time!",
        // MealType.DRINKS,
        // List.of(i87, i88, i89, i78, i23),
        // "vegan-iced-cappuchino.jpg");

        // recipeRepository.save(r18);

        // Ingredient i90 = new Ingredient("Raspberries", FoodType.FRUITS);
        // ingredientRepository.saveAll(List.of(i90));

        // Recipe r19 = new Recipe("Vegan Berry IceCream ", 5,
        // "(1) Freeze bananas overnight.;\n" +
        // "(2) Put in frozen bananas, frozen berries, and almond milk into a
        // blender.;\n"
        // +
        // "(3) Put in frozen bananas, frozen berries, and almond milk into a
        // blender.;\n"
        // +
        // "(4) Serve and enjoy! \n",
        // "This Vegan Berry Nice Cream is the perfect dessert for hot summer days. This
        // dairy-free and sugar-free ice cream can be made in a blender in just minutes!
        // Plus, it’s quick, easy, and only requires 3 ingredients!",
        // MealType.DESSERTS,
        // List.of(i90, i15, i13, i23, i78),
        // "berry-nice-cream.jpg");

        // recipeRepository.save(r19);

        // Ingredient i91 = new Ingredient("Radishes", FoodType.OTHER);
        // Ingredient i92 = new Ingredient("Cucumbers", FoodType.OTHER);
        // Ingredient i93 = new Ingredient("Dried Dill", FoodType.OTHER);
        // ingredientRepository.saveAll(List.of(i91, i92, i93));

        // Recipe r20 = new Recipe("Vegan Creamy Radish Salad", 10,
        // "(1) Cut the radishes and cucumbers.;\n" +
        // "(2) Combine mayo with seasoning.;\n"
        // +
        // "(3) Toss everything together.;\n"
        // +
        // "(4) Serve and enjoy! \n",
        // "This Vegan Creamy Radish Salad is a delicious, refreshing, and healthy dish
        // you can make in just minutes! Using only a few ingredients, this is a recipe
        // you must try!",
        // MealType.SALADS,
        // List.of(i91, i92, i93, i47, i32, i41, i5, i9),
        // "vegan-radish-salad.jpg");

        // recipeRepository.save(r20);

        // Ingredient i94 = new Ingredient("Green Lentils ",
        // FoodType.CEREALS_AND_PULSES);
        // Ingredient i95 = new Ingredient("Green Bell Pepper",
        // FoodType.SPICES_AND_HERBS);
        // Ingredient i96 = new Ingredient("Turmeric ", FoodType.OTHER);
        // Ingredient i97 = new Ingredient("Chili Powder ", FoodType.SPICES_AND_HERBS);
        // ingredientRepository.saveAll(List.of(i94, i95, i96, i97));

        // Recipe r21 = new Recipe("Easy Moroccan Lentils", 40,
        // "(1) Saute the onions and garlic in your Instant Pot.;\n" +
        // "(2) Add all remaining ingredients and stir in.;\n"
        // +
        // "(3) Cook for 30 minutes on manual setting.;\n"
        // +
        // "(4) Enjoy! \n",
        // "These Easy Moroccan Lentils are healthy, flavorful, and delicious for lunch
        // or dinner! Plus, they only take 30 minutes to cook!",
        // MealType.SALADS,
        // List.of(i94, i95, i96, i97, i31, i32, i2, i59, i79),
        // "Moroccan-lentils.jpg");

        // recipeRepository.save(r21);

        // Ingredient i98 = new Ingredient("Tea", FoodType.OTHER);
        // Ingredient i99 = new Ingredient("Vegan Whipped Cream", FoodType.OTHER);
        // ingredientRepository.saveAll(List.of(i98, i99));

        // Recipe r22 = new Recipe("Vegan Chai Latte", 10,
        // "(1) Brew tea according to instructions. Add almond milk and maple syrup and
        // stir.;\n" +
        // "(2) Top with whipped cream;\n"
        // +
        // "(3) Enjoy! \n",
        // "This Vegan Chai Latte is the perfect drink for the winter! Cinnamon, chai
        // spices, and coconut whipped cream really make this drink delicious.\nCinnamon
        // and the chai flavor really bring out the best of this drink. And with vegan
        // whipped cream on top? Sounds too good to be true!",
        // MealType.DRINKS,
        // List.of(i98, i99, i23, i70),
        // "vegan-chai-latte.jpg");

        // recipeRepository.save(r22);

        // Recipe r23 = new Recipe("Dalgona Coffe ", 5,
        // "(1) Mix the instant coffee, sugar, and water together in a medium-sized bowl
        // until peaks form.;\n"
        // +
        // "(2) In a cup or mug, fill two-thirds of the cup with cold or hot milk. I
        // used almond milk.;\n"
        // +
        // "(3) Fill the rest of the cup with the coffee foam.; " +
        // "(4) Sprinkle over cinnamon or crushed Dalgona on top or serve as is.; "
        // +
        // "(5) Enjoy! ",
        // "This Dalgona Coffee is a delicious drink with a base of milk, topped with
        // smooth coffee foam! This highly popular Korean drink can be served hot or
        // cold!\nDalgona is the name of a Korean honeycomb toffee that was a popular
        // street food in the 70s to 80s. You can make this and sprinkle chunks of it on
        // top of your coffee!",
        // MealType.DRINKS,
        // List.of(i51, i88, i49),
        // "dalgona-coffee.jpg");

        // recipeRepository.save(r23);

        // Ingredient i101 = new Ingredient("Granulated Sugar",
        // FoodType.SUGAR_AND_SUGAR_PRODUCTS);
        // Ingredient i102 = new Ingredient("Brown Sugar",
        // FoodType.SUGAR_AND_SUGAR_PRODUCTS);
        // Ingredient i103 = new Ingredient("Vegetable Oil", FoodType.OTHER);
        // Ingredient i104 = new Ingredient("All-Prpose Flour", FoodType.OTHER);
        // Ingredient i105 = new Ingredient("Ground Cinnamon ", FoodType.OTHER);
        // Ingredient i106 = new Ingredient("Chopped Walnuts ",
        // FoodType.CEREALS_AND_PULSES);

        // ingredientRepository.saveAll(List.of(i101, i102, i103, i104, i105, i106));

        // Recipe r24 = new Recipe("Vegan Banana Bread", 60,
        // "(1) Preheat oven to 350° F.;\n" +
        // "(2) Spray a loaf pan with oil and line with parchment paper.;\n"
        // +
        // "(3) Mash bananas in a bowl and add the brown sugar, vegetable oil, sugar,
        // and vanilla extract, then mix.;\n"
        // +
        // "(4) In a separate bowl, put in the flour, baking soda, salt, and cinnamon,
        // and mix. Mix in the walnuts.;"
        // +
        // "(5) Pour the wet ingredients into the dry ingredients and fold until well
        // combined.;"
        // +
        // "(6) Pour batter into the loaf pan.;" +
        // "(7) Spread the batter evenly across the pan.;" +
        // "(8) Bake for 50 minutes or until when you poke a toothpick in it, nothing
        // sticks.;"
        // +
        // "(9) Let it cool completely.;" +
        // "(10) Enjoy!;",
        // "This Vegan Banana Bread is perfect for breakfast or as a snack. It’s super
        // easy and absolutely delicious!\nI’ve been working on a vegan banana bread
        // recipe for a while now, and I’ve finally created it! It’s moist, flavorful,
        // and you would never guess there’s no dairy or egg in it! I enjoy this banana
        // bread with chopped nuts, but you can put any kind of dried fruit or even
        // chocolate chips in.",
        // MealType.BREAD_AND_CAKES,
        // List.of(i62, i5, i53, i78, i101, i102, i103, i104, i105, i106),
        // "vegan-banana-bread.jpg");

        // recipeRepository.save(r24);

        // Ingredient i107 = new Ingredient("Old-Fashioned Oats",
        // FoodType.CEREALS_AND_PULSES);
        // Ingredient i108 = new Ingredient("Toppings", FoodType.OTHER);

        // ingredientRepository.saveAll(List.of(i107, i108));

        // Recipe r25 = new Recipe("Oatmeal", 10,
        // "(1) Put the oats, milk, water, coconut oil, and salt into a pot and place it
        // on the stove.;\n"
        // +
        // "(2) Mix, and cook until creamy.;\n"
        // +
        // "(3) Transfer oatmeal into a bowl and add your toppings.;\n"
        // +
        // "(4) Enjoy!;",
        // "Learn how to make the best oatmeal. This recipe will help you turn oats into
        // a creamy and delicious breakfast!\nOne of my favorite breakfasts to make is
        // oatmeal! It is easy to make and only needs a few ingredients. To keep it
        // vegan, I use almond milk but you can use any nut milk you like. Plus, you can
        // top it with anything, so you never get bored with the flavors!",
        // MealType.DESSERTS,
        // List.of(i36, i49, i5, i25, i107, i108),
        // "the-best-oatmeal.jpg");

        // recipeRepository.save(r25);
    }

}
