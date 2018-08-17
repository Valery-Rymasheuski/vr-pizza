package com.example.rymasheuski.valery.vrpizza.dummy;

import com.example.rymasheuski.valery.vrpizza.R;
import com.example.rymasheuski.valery.vrpizza.model.Food;
import com.example.rymasheuski.valery.vrpizza.model.Pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valery on 2.8.18.
 */

public class DummyDatabaseContent {

    private static long templateId = 0;

    private static List<List<Food>> list;


    public static List<List<Food>> getAllFoods(){
        if(list != null){
            return list;
        }
        list = new ArrayList<>();

        list.add(getPizzaList());
        list.add(getChickenList());
        list.add(getBreadList());
        list.add(getSaladList());
        list.add(getDessertList());
        list.add(getDrinkList());
        list.add(getSauceList());

        return list;
    }


    public static List<Food> getPizzaList(){
        List<Food> list = new ArrayList<>();
        list.add(createPizza("Маргарита", "Соус для пиццы, Сыр моцарелла", 8.9, 400));
        list.add(createPizza("Гавайская", "Соус для пиццы, Курица, Сыр моцарелла, Ананас", 8.9, 415));
        list.add(createPizza("Ветчина и грибы", "Соус для пиццы, Сыр моцарелла, Ветчина, Шампиньоны", 8.9, 425));
        list.add(createPizza("Пепперони", "Соус для пиццы, Сыр моцарелла, Пеппероны", 8.9, 365));
        list.add(createPizza("Овощная", "Томаты, Соус для пиццы, Лук, Шампиньоны, Сладкий перец, Оливки, Сыр моцарелла", 10.9, 430));
        list.add(createPizza("Барбекю", "Курица, Лук, Бекон, Шампиньоны, Соус барбекю, Сыр моцарелла", 10.9, 410));
        list.add(createPizza("Спайси", "Томаты, Соус для пиццы, Сыр моцарелла, Пеппероны, Бекон, Халапеньо", 10.9, 430));
        list.add(createPizza("Супер пепперони", "Соус для пиццы, Сыр моцарелла, Пепперони", 10.9, 455));
        list.add(createPizza("Прованс", "Томаты, Голубой сыр, Сыр моцарелла, Крем фреш, Ветчина, Пепперони", 10.9, 395));
        list.add(createPizza("Карбонара", "Лук, Крем фреш, Ветчина,  Бекон, Шампиньоны, Сыр моцарелла", 10.9, 410));


        return list;
    }

    public static List<Food> getChickenList(){
        List<Food> list = new ArrayList<>();
        list.add(createFood("Special Чикен Пепперони-Перчик", "Соус для пиццы, Томаты, Сыр моцарелла, Сладкий перец", 7.4, 200));
        list.add(createFood("Крылышки острые", "Соус Терияки 25г, Сальса соус", 8.4, 300));
        list.add(createFood("Special Чикен Бекон-Халапеньо", "Соус для пиццы, Халапеньо, Сыр моцарелла, Бекон", 7.4, 190));



        return list;
    }

    public static List<Food> getBreadList(){
        List<Food> list = new ArrayList<>();
        list.add(createFood("Хлебец со шпинатом и фетой", "Чеддер, Сыр моцарелла, Шпинат, Фета", 8.9, 400));
        list.add(createFood("Хлебец с сырной начинкой", "Чеддер, Сыр моцарелла", 8.4, 385));


        return list;
    }

    public static List<Food> getSaladList(){
        List<Food> list = new ArrayList<>();
        list.add(createFood("Салат с курицей", "Микс салатов, Пармезан, Томаты, Оливковое масло, Курица", 5.9, 195));
        list.add(createFood("Овощной салат", "Микс салатов, Томаты, Оливковое масло, Шпинат, Сладкий перец", 3.9, 164));


        return list;
    }

    public static List<Food> getDessertList(){
        List<Food> list = new ArrayList<>();
        list.add(createFood("Лава кейк", null, 4.9, 95));
        list.add(createFood("Байтсы с корицей", null, 1.9, 195));


        return list;
    }


    public static List<Food> getDrinkList(){
        List<Food> list = new ArrayList<>();
        list.add(createFood("RICH Мультифрукт Сок 1 л", null, 3.7, 1000));
        list.add(createFood("RICH Томатный Сок 1 л", null, 3.7, 1000));


        return list;
    }

    public static List<Food> getSauceList(){
        List<Food> list = new ArrayList<>();
        list.add(createFood("Соус барбекю", null, 0.7, 25));
        list.add(createFood("Соус Томатный", null, 0.7,  25));


        return list;
    }



    private static Pizza createPizza(String name, String desc, double price, int size){
        Pizza pizza = new Pizza();
        fillAttributes(pizza, name, desc, price, size);
        pizza.setImageId(R.drawable.pizza1);
        return pizza;
    }

    private static Food createFood(String name, String desc, double price, int size){
        Food food = new Food();
        fillAttributes(food, name, desc, price, size);
        return food;
    }

    private static void fillAttributes(Food food, String name, String desc, double price, int size){
        food.setId(generateId());
        food.setName(name);
        food.setDescription(desc);
        food.setPrice((int) (price * 100));
        food.setSize(size);
    }

    private static long generateId(){
        return ++templateId;
    }

}
