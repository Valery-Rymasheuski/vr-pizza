package com.example.rymasheuski.valery.vrpizza.model.database;

import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.FoodType;



import java.util.Arrays;
import java.util.List;

/**
 * Created by valery on 29.8.18.
 */

public class InitialDataHelper {

    private InitialDataHelper() {
    }

    public static List<FoodType> getInitialFoodTypes(){
        return Arrays.asList(new FoodType(1, "pizza_tab", true),
                new FoodType(2, "chicken_tab", true),
                new FoodType(3, "bread_tab", true),
                new FoodType(4, "salads_tab", true),
                new FoodType(5, "desserts_tab", true),
                new FoodType(6, "drinks_tab", true),
                new FoodType(7, "sauces_tab", true)
                );
    }


    public static List<Food> getInitialFoods(){
        return Arrays.asList(createPizza(1, "Маргарита", "Соус для пиццы, Сыр моцарелла", 8.9, 400),
            createPizza(2, "Гавайская", "Соус для пиццы, Курица, Сыр моцарелла, Ананас", 8.9, 415),
            createPizza(3, "Ветчина и грибы", "Соус для пиццы, Сыр моцарелла, Ветчина, Шампиньоны", 8.9, 425),
            createPizza(4, "Пепперони", "Соус для пиццы, Сыр моцарелла, Пеппероны", 8.9, 365),
            createPizza(5, "Овощная", "Томаты, Соус для пиццы, Лук, Шампиньоны, Сладкий перец, Оливки, Сыр моцарелла", 10.9, 430),
            createPizza(6, "Барбекю", "Курица, Лук, Бекон, Шампиньоны, Соус барбекю, Сыр моцарелла", 10.9, 410),
            createPizza(7,  "Спайси", "Томаты, Соус для пиццы, Сыр моцарелла, Пеппероны, Бекон, Халапеньо", 10.9, 430),
            createPizza(8, "Супер пепперони", "Соус для пиццы, Сыр моцарелла, Пепперони", 10.9, 455),
            createPizza(9,"Прованс", "Томаты, Голубой сыр, Сыр моцарелла, Крем фреш, Ветчина, Пепперони", 10.9, 395),
            createPizza(10,"Карбонара", "Лук, Крем фреш, Ветчина,  Бекон, Шампиньоны, Сыр моцарелла", 10.9, 410),

            createFood(101, 2, "Special Чикен Пепперони-Перчик", "Соус для пиццы, Томаты, Сыр моцарелла, Сладкий перец", 7.4, 200),
            createFood(102, 2, "Крылышки острые", "Соус Терияки 25г, Сальса соус", 8.4, 300),
            createFood(103, 2, "Special Чикен Бекон-Халапеньо", "Соус для пиццы, Халапеньо, Сыр моцарелла, Бекон", 7.4, 190),

            createFood(201, 3, "Хлебец со шпинатом и фетой", "Чеддер, Сыр моцарелла, Шпинат, Фета", 8.9, 400),
            createFood(202, 3, "Хлебец с сырной начинкой", "Чеддер, Сыр моцарелла", 8.4, 385),

            createFood(301, 4, "Салат с курицей", "Микс салатов, Пармезан, Томаты, Оливковое масло, Курица", 5.9, 195),
            createFood(302, 4, "Овощной салат", "Микс салатов, Томаты, Оливковое масло, Шпинат, Сладкий перец", 3.9, 164),

            createFood(401, 5, "Лава кейк", null, 4.9, 95),
            createFood(402, 5, "Байтсы с корицей", null, 1.9, 195),

            createFood(501, 6, "RICH Мультифрукт Сок 1 л", null, 3.7, 1000),
            createFood(502, 6, "RICH Томатный Сок 1 л", null, 3.7, 1000),

            createFood(601, 7, "Соус барбекю", null, 0.7, 25),
            createFood(602, 7, "Соус Томатный", null, 0.7,  25)



            );
    }



    private static Food createPizza(long id, String name, String desc, double price, int size){
        Food pizza = new Food();
        fillAttributes(pizza, id, 1, name, desc, price, size);
        pizza.setImageId(R.drawable.pizza1);
        pizza.setHasOptions(true);
        return pizza;
    }

    private static Food createFood(long id, int typeId, String name, String desc, double price, int size){
        Food food = new Food();
        fillAttributes(food, id, typeId, name, desc, price, size);
        return food;
    }

    private static void fillAttributes(Food food, long id, int typeId, String name, String desc, double price, int size){
        food.setId(id);
        food.setTypeId(typeId);
        food.setName(name);
        food.setDescription(desc);
        food.setPrice((int) (price * 100));
        food.setSize(size);
        food.setActive(true);
    }
}
