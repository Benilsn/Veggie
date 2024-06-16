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
                User admin = new User(null, "Admin", "admin@gmail.com", encoder.encode("123"),
                                new SimpleGrantedAuthority("ROLE_ADMIN"), "photoless.jpg", true);
                User user1 = new User(null, "Joao", "usuario1@gmail.com", encoder.encode("123"),
                                new SimpleGrantedAuthority("ROLE_USER"),
                                "photoless.jpg", true);
                User user2 = new User(null, "Joao", "usuario2@gmail.com", encoder.encode("123"),
                                new SimpleGrantedAuthority("ROLE_USER"),
                                "photoless.jpg", true);

                userRepository.saveAll(List.of(admin, user1, user2));
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

                Recipe r3 = new Recipe("Vegan X-Burguer", 35,
                                List.of("(1) Mix ground protein with liquid smoke\n" +
                                                "(2) When shaping the patty, make sure to press it hard so it stays together"
                                                +
                                                "when cooking\n"
                                                + "(3) Cook ground protein patty on a pan or in an oven\n" +
                                                "(4)Assemble burger by placing the patty, pickles, onions, tomatoes, and"
                                                +
                                                "lettuce in between two buns.\n"),
                                "With vegan protein, you can make this delicious Vegan Impossible Whopper" +
                                                "Copycat Recipe! You only need a handful of ingredients.",
                                MealType.OTHER, "veggie-burguer.jpg",
                                List.of("Lettuce\nRed Onion\nLiquid Smoke\nPickle\nVegan Mayonnaise\nBun"));

                recipeService.create(r3);

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
                                                "to begin preparing my dishes. Mashed potatoes and gravy have already been"
                                                +
                                                "perfected, and I have more ideas on the way. You can never have too many side"
                                                +
                                                "dishes though, so I decided on these roasted sweet potatoes! They’re soft,"
                                                +
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
                                                "pancakes. Maybe I’m a bit too obsessed with pumpkin. But, hey, it’s Fall!"
                                                +
                                                "These pancakes are so delicious! They’re fluffy and the pumpkin flavor is"
                                                +
                                                "very apparent. I served them with maple syrup and chopped pecans and my"
                                                +
                                                "family loved it!",
                                MealType.SNACKS, "pumpkin-pancakes.jpg",
                                List.of("Flour\nBaking Powder\nBaking Soda\nBrown Sugar\nPumpkin Pie Spice\nPumpkin Puree\nSalt\nAlmond Milk\nVanilla extract\nOlive Oil"));

                recipeService.create(r7);

                Recipe r8 = new Recipe("Vegan Stuffing", 30,
                                List.of("(1) Cut bread into small squares;\n" +
                                                "(2) Toast in the oven;\n" +
                                                "(3) Chop celery and onions;\n" +
                                                "(4) Pour oil into a pan over medium-high heat;\n" +
                                                "(5) Sauté garlic, onions, and celery until onions are translucent;" +
                                                "(6) In a large bowl, add toasted bread, sautéd veggies, and seasoning. Toss"
                                                +
                                                "until mixed;"
                                                +
                                                "(7) Place in a pan and spread evenly;" +
                                                "(8) Pour vegetable stock over;" +
                                                "(9) Cover pan with aluminum foil;" +
                                                "(10) Bake in the oven for 25 minutes. Afterward, take off foil and cook for"
                                                +
                                                "another 10 minutes;"
                                                +
                                                "(11) Let cool;" +
                                                "(12) Enjoy!"),
                                "Nothing says holiday meals like stuffing. This Vegan Stuffing is the perfect" +
                                                "no-dairy version of the classic favorite!\nThis stuffing is 100% dairy-free,"
                                                +
                                                "soft with a crispy top, and lots of flavor! Plus, it’s easy and barely takes"
                                                +
                                                "any effort!",
                                MealType.SNACKS, "vegan-stuffing.jpg",
                                List.of("Bread\nCelery\nFresh Herbs\nOnion\nOlive Oil\nGarlic"));

                recipeService.create(r8);

                Recipe r9 = new Recipe("Roasted Cauliflower", 40,
                                List.of("(1) Wash and cut cauliflower into similar-sized pieces;\n" +
                                                "(2) Place cauliflower in a bowl and cover with oil and seasoning;\n" +
                                                "(3) Toss until well covered;\n" +
                                                "(4) Spread cauliflower over a baking sheet lined with parchment paper;\n"
                                                +
                                                "(5) Cook for 30-35 minutes or until tender and browning;" +
                                                "(6) Wait until cool;" + "(7) Enjoy!"),
                                "Healthy, flavorful, and delicious, this Roasted Cauliflower is a perfect" +
                                                "side dish to your dinners. This recipe is sure to leave you wanting more!\n",
                                MealType.VEGETABLES, "roasted-cauliflower.jpg",
                                List.of("Cauliflower\nGarlic\nOlive Oil\nSalt"));

                recipeService.create(r9);

                Recipe r10 = new Recipe("Pumpkin Spice Latte", 10,
                                List.of("(1) Wash and cut cauliflower into similar-sized pieces;\n" +
                                                "(2) Place cauliflower in a bowl and cover with oil and seasoning;\n" +
                                                "(3) Toss until well covered;\n" +
                                                "(4) Spread cauliflower over a baking sheet lined with parchment paper;\n"
                                                +
                                                "(5) Cook for 30-35 minutes or until tender and browning;" +
                                                "(6) Wait until cool;" + "(7) Enjoy!"),
                                "This Vegan Pumpkin Spice Latte is a dairy-free twist on a fall favorite." +
                                                "Flavorful, and delicious, this drink is much better than store-bought"
                                                +
                                                "equivalents.\nAs soon as it turns fall, I will be having this drink almost"
                                                +
                                                "week. It’s warm, comforting, and the perfect thing to have as you cozy up in"
                                                +
                                                "your favorite sweater and read a book. My favorite about thing about this"
                                                +
                                                "drink is how easy it is!",
                                MealType.VEGETABLES, "vegan-pumpkin-spice-latte.jpg",
                                List.of("Maple Syrup\nPumpkin Pie Spice\nPumpkin Puree\nCoffee\nVanilla extract\nAlmond Milk"));

                recipeService.create(r10);

                Recipe r11 = new Recipe("Vegan Gravy", 10,
                                List.of("(1) Mix all ingredients in a large pot.;\n" +
                                                "(2) Place pot on the stove over medium heat.;\n" +
                                                "(3) Stir when bubbles are visible until the sauce becomes thicker.;\n"
                                                +
                                                "(4) Let cool to thicken further.;\n"
                                                +
                                                "(5) Serve over mashed potatoes.;"),
                                "This Vegan Gravy is made with pantry ingredients and cooks in five minutes." +
                                                "It’s smooth, flavorful, and absolutely delicious! Perfect addition to your"
                                                +
                                                "holiday meals!\nThis gravy is full of flavor, creamy, and the perfect sauce"
                                                +
                                                "to serve on your holiday table.",
                                MealType.VEGETABLES, "vegan-gravy.jpg",
                                List.of("Vegetable stock\nThyme\nSoy sauce\nCauliflower\nOnion\nGarlic\nNutricional Yeast\nSalt\nBlack Pepper"));

                recipeService.create(r11);

                Recipe r12 = new Recipe("Vegan Mashed Potatoes", 30,
                                List.of("(1) Mix all ingredients in a large pot.;\n" +
                                                "(2) Place pot on the stove over medium heat.;\n" +
                                                "(3) Stir when bubbles are visible until the sauce becomes thicker.;\n"
                                                +
                                                "(4) Let cool to thicken further.;\n"
                                                +
                                                "(5) Serve over mashed potatoes.;"),
                                "These Vegan Mashed Potatoes are a dairy-free version of the classic side" +
                                                "dish. Creamy, fluffy, and super easy, made with pantry ingredients, no vegan"
                                                +
                                                "butter needed!\nMy favorite part about these mashed potatoes is that they’re"
                                                +
                                                "creamy, smooth, fluffy, and so easy to make!",
                                MealType.OTHER, "mashed-potatoes.jpg",
                                List.of("Russet Potato\nOlive Oil\nGarlic\n\nSalt\nBlack Pepper\nAlmond Milk"));

                recipeService.create(r12);

                Recipe r13 = new Recipe("Pico de Gallo", 30,
                                List.of("(1) Dice the tomatoes, pepper, and onion into small pieces.;\n" +
                                                "(2) Place in a large bowl.;\n" +
                                                "(3) Place in a large bowl.;\n"
                                                +
                                                "(4) Serve with chips or in a taco.;\n"
                                                +
                                                "(5) Enjoy!;"),
                                "Made with plump and juicy tomatoes, fresh pepper and onion, this Pico de" +
                                                "Gallo is delicious served with tortilla chips or in vegan tacos!\nEvery"
                                                +
                                                "Tuesday, we make tacos. And this Pico de Gallo is a new addition! It’s so"
                                                +
                                                "easy to make and perfect as a topping. This recipe is quick and easy,"
                                                +
                                                "requiring only a few ingredients." +
                                                "creamy, smooth, fluffy, and so easy to make!",
                                MealType.OTHER, "pico-de-gallo.jpg",
                                List.of("Lime\nOlive Oil\nOnion\n\nSalt\nBlack Pepper\nParsley"));

                recipeService.create(r13);

                Recipe r14 = new Recipe("Vegan Cornbread", 35,
                                List.of("(1) Mix all dry ingredients together in a bowl until combined.;\n" +
                                                "(2) Add wet ingredients and mix.;\n" +
                                                "(3) Pour into a lightly greased skillet or round cake pan.;\n"
                                                +
                                                "(4) Bake at 400 for 23-25 minutes or until a toothpick comes out clean.;\n"
                                                +
                                                "(5) Enjoy!;"),
                                "This Vegan Cornbread is fluffy, moist, and flavorful. Requiring only a few" +
                                                "ingredients, you can have a delicious side dish in no time!" +
                                                "creamy, smooth, fluffy, and so easy to make!",
                                MealType.BREAD_AND_CAKES, "vegan-cornbread.jpg",
                                List.of("Yellow Cornmeal\nFlour\nBaking Powder\nSugar\nCoconut oil\nSoy Milk"));

                recipeService.create(r14);

                Recipe r15 = new Recipe("Vegan Breakfast Smoothie", 35,
                                List.of("(1) Place all ingredients into a blender.;\n" +
                                                "(2) Blend until smooth.;\n" +
                                                "(5) Enjoy!;"),
                                "Creamy, energizing, and super healthy, this Vegan Breakfast Smoothie is a" +
                                                "perfect and easy drink to make when you don’t have a lot of time. Simply add"
                                                +
                                                "all ingredients to the blender and blend!",
                                MealType.SAUCES_AND_ACCOMPANIMENTS, "vegan-breakfast-smoothie.jpg",
                                List.of("Spinach\nBanana\nGinger\nStrawberry\nSoy Milk"));

                recipeService.create(r15);

                Recipe r16 = new Recipe("Vegan Edible Cookie Dough", 30,
                                List.of("(1) In a food processor, blend the chickpeas until smooth.;\n" +
                                                "(2) Add oats, maple syrup, peanut butter, and almond extract and blend.;\n"
                                                +
                                                "(3) Transfer to a bowl and add mix-ins. Mix until combined;\n"
                                                +
                                                "(4) Serve in a bowl and enjoy!;\n"),
                                "Smooth, sweet, and creamy, this Vegan Edible Cookie Dough is an amazing and" +
                                                "easy dessert or snack! Simply serve in a bowl and enjoy!",
                                MealType.DESSERTS, "Vegan-Edible-Cookie-Dough.jpg",
                                List.of("Chickpeas\nNut Butter\nAlmond Extract\nOats\nMaple Syrup\nChocolate Chips"));

                recipeService.create(r16);

                Recipe r17 = new Recipe("Vegan Avocado Hummus", 5,
                                List.of("(1) Add all ingredients in a food processor or blender. Blend until" +
                                                "smooth.;\n" +
                                                "(2) Add more water if needed.;\n"
                                                +
                                                "(3) Serve with chips, crackers, or carrots.;\n"
                                                +
                                                "(4) Enjoy!\n"),
                                "This Vegan Avocado Hummus is creamy, light, and absolutely delicious! Using" +
                                                "only a few ingredients, this dip is like guacamole and hummus combined—a"
                                                +
                                                "win-win for everyone!",
                                MealType.SAUCES_AND_ACCOMPANIMENTS, "avocadohummus.jpg",
                                List.of("Avocado\nTahini\nGarlic Cloves\nLemon Juice"));

                recipeService.create(r17);

                Recipe r18 = new Recipe("Vegan Iced Cappuccino ", 365,
                                List.of("(1) Freeze the banana overnight.;\n" +
                                                "(2) Stir the instant coffee mix and cold water together to make cold coffee.;\n"
                                                +
                                                "(3) Blend together the frozen bananas, ice cubes, cold coffee, plant-based milk, and dates.;\n"
                                                +
                                                "(4) Serve and enjoy! \n"),
                                "This flavorful Vegan Iced Cappuccino is the ultimate drink for hot days!" +
                                                "Using cold coffee, bananas, your favorite plant-based milk, and Medjool"
                                                +
                                                "dates, this recipe is one you can make in no time!"
                                                +
                                                "all ingredients to the blender and blend!",
                                MealType.DRINKS, "vegan-iced-cappuchino.jpg",
                                List.of("Ice Cubes\nInstant Coffe Mix\nMedjool Dates\nBanana\nSoy Milk"));

                recipeService.create(r18);

                Recipe r19 = new Recipe("Vegan Berry IceCream ", 5,
                                List.of("(1) Freeze bananas overnight.;\n" +
                                                "(2) Put in frozen bananas, frozen berries, and almond milk into a blender.;\n"
                                                +
                                                "(3) Serve and enjoy! \n"),
                                "Creamy, energizing, and super healthy, this Vegan Breakfast Smoothie is a" +
                                                "perfect and easy drink to make when you don’t have a lot of time. Simply add"
                                                +
                                                "all ingredients to the blender and blend!",
                                MealType.DESSERTS, "berry-nice-cream.jpg",
                                List.of("Raspberries\nStrawberry\nCranberry\nSoy Milk\nBanana"));

                recipeService.create(r19);

                Recipe r20 = new Recipe("Vegan Creamy Radish Salad", 10,
                                List.of("(1) Cut the radishes and cucumbers.;\n" +
                                                "(2) Combine mayo with seasoning.;\n"
                                                +
                                                "(3) Toss everything together.;\n"
                                                +
                                                "(4) Serve and enjoy! \n"),
                                "This Vegan Creamy Radish Salad is a delicious, refreshing, and healthy dish" +
                                                "you can make in just minutes! Using only a few ingredients, this is a recipe"
                                                +
                                                "you must try!",
                                MealType.SALADS, "vegan-radish-salad.jpg",
                                List.of("Radishes\nCucumbers\nDried Dill\nVegan Mayonnaise\nGarlic\nParsley\nSalt\nBlack Pepper"));

                recipeService.create(r20);

                Recipe r21 = new Recipe("Easy Moroccan Lentils", 40,
                                List.of("(1) Saute the onions and garlic in your Instant Pot.;\n" +
                                                "(2) Add all remaining ingredients and stir in.;\n"
                                                +
                                                "(3) Cook for 30 minutes on manual setting.;\n"
                                                +
                                                "(4) Enjoy! \n"),
                                "These Easy Moroccan Lentils are healthy, flavorful, and delicious for lunch" +
                                                "or dinner! Plus, they only take 30 minutes to cook!",
                                MealType.SALADS, "Moroccan-lentils.jpg",
                                List.of("Green Lentils\nGreen Bell Pepper\nTurmeric\nChili Powder\nTomato\nGarlic\nOnion\nCumin\nGinger"));

                recipeService.create(r21);

                Recipe r22 = new Recipe("Vegan Chai Latte", 10,
                                List.of("(1) Brew tea according to instructions. Add almond milk and maple syrup and stir.;\n"
                                                +
                                                "(2) Top with whipped cream;\n"
                                                +
                                                "(3) Enjoy! \n"),
                                "This Vegan Chai Latte is the perfect drink for the winter! Cinnamon, chai" +
                                                "spices, and coconut whipped cream really make this drink delicious.\nCinnamon"
                                                +
                                                "and the chai flavor really bring out the best of this drink. And with vegan"
                                                +
                                                "whipped cream on top? Sounds too good to be true!",
                                MealType.DRINKS, "vegan-chai-latte.jpg",
                                List.of("Tea\nVegan Whipped Cream\nSoy Milk\nMaple Syrup"));

                recipeService.create(r22);

                Recipe r23 = new Recipe("Dalgona Coffe ", 5,
                                List.of("(1) Mix the instant coffee, sugar, and water together in a medium-sized bowl until peaks form.;\n"
                                                +
                                                "(2) In a cup or mug, fill two-thirds of the cup with cold or hot milk. I used almond milk.;\n"
                                                +
                                                "(3) Fill the rest of the cup with the coffee foam.; " +
                                                "(4) Sprinkle over cinnamon or crushed Dalgona on top or serve as is.; "
                                                +
                                                "(5) Enjoy! "),
                                "This Dalgona Coffee is a delicious drink with a base of milk, topped with" +
                                                "smooth coffee foam! This highly popular Korean drink can be served hot or"
                                                +
                                                "cold!\nDalgona is the name of a Korean honeycomb toffee that was a popular"
                                                +
                                                "street food in the 70s to 80s. You can make this and sprinkle chunks of it on"
                                                +
                                                "top of your coffee!",
                                MealType.DRINKS, "dalgona-coffee.jpg",
                                List.of("Coconut Sugar\nInstant Coffe Mix\nAlmond Milk"));

                recipeService.create(r23);

                Recipe r24 = new Recipe("Vegan Banana Bread", 60,
                                List.of("(1) Preheat oven to 350° F.;\n" +
                                                "(2) Spray a loaf pan with oil and line with parchment paper.;\n"
                                                +
                                                "(3) Mash bananas in a bowl and add the brown sugar, vegetable oil, sugar, and vanilla extract, then mix.;\n"
                                                +
                                                "(4) In a separate bowl, put in the flour, baking soda, salt, and cinnamon, and mix. Mix in the walnuts.;"
                                                +
                                                "(5) Pour the wet ingredients into the dry ingredients and fold until well combined.;"
                                                +
                                                "(6) Pour batter into the loaf pan.;" +
                                                "(7) Spread the batter evenly across the pan.;" +
                                                "(8) Bake for 50 minutes or until when you poke a toothpick in it, nothing sticks.;"
                                                +
                                                "(9) Let it cool completely.;" +
                                                "(10) Enjoy!;"),
                                "This Vegan Banana Bread is perfect for breakfast or as a snack. It’s super" +
                                                "easy and absolutely delicious!\nI’ve been working on a vegan banana bread"
                                                +
                                                "recipe for a while now, and I’ve finally created it! It’s moist, flavorful,"
                                                +
                                                "and you would never guess there’s no dairy or egg in it! I enjoy this banana"
                                                +
                                                "bread with chopped nuts, but you can put any kind of dried fruit or even"
                                                +
                                                "chocolate chips in.",
                                MealType.BREAD_AND_CAKES, "vegan-banana-bread.jpg",
                                List.of("Baking Soda\nSalt\nVanilla extract\nBanana\nGranulated Sugar\nBrown Sugar\nVegetable Oil\nAll-Prpose Flour\nGround Cinnamon\nChopped Walnuts"));

                recipeService.create(r24);

                Recipe r25 = new Recipe("Oatmeal", 10,
                                List.of("(1) Put the oats, milk, water, coconut oil, and salt into a pot and place it on the stove.;\n"
                                                +
                                                "(2) Mix, and cook until creamy.;\n"
                                                +
                                                "(3) Transfer oatmeal into a bowl and add your toppings.;\n"
                                                +
                                                "(4) Enjoy!;"),
                                "Learn how to make the best oatmeal. This recipe will help you turn oats into" +
                                                "a creamy and delicious breakfast!\nOne of my favorite breakfasts to make is"
                                                +
                                                "oatmeal! It is easy to make and only needs a few ingredients. To keep it"
                                                +
                                                "vegan, I use almond milk but you can use any nut milk you like. Plus, you can"
                                                +
                                                "top it with anything, so you never get bored with the flavors!",
                                MealType.DESSERTS, "the-best-oatmeal.jpg",
                                List.of("Old-Fashioned Oats\nToppings\nWater\nAlmond Milk\nSalt\nCoconut oil"));

                recipeService.create(r25);

        }

}
