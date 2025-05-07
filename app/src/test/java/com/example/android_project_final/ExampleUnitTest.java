package com.example.android_project_final;

import org.junit.Test;

import static org.junit.Assert.*;


import android.app.Application;
import android.content.Context;

import com.example.android_project_final.database.entities.Ingredients;
import com.example.android_project_final.database.entities.Meal;
import com.example.android_project_final.database.entities.User;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testMealConstructorAndGetters() {
        Meal meal1 = new Meal("Chicken Bowl", 500, 35, 20, 15);
        Meal meal2 = new Meal("Steak & Potatoes", 650, 55, 10, 45);

        assertEquals("chicken bowl", meal1.getMealName());
        assertEquals(500, meal1.getCalories());
        assertEquals(35, meal1.getProtein());
        assertEquals(20, meal1.getFat());
        assertEquals(15, meal1.getCarbs());
    }

    @Test
    public void testMealSetters() {
        Meal meal2 = new Meal("Steak & Potatoes", 650, 55, 10, 45);

        meal2.setMealName("Sandwich");
        meal2.setCalories(700);
        meal2.setProtein(40);
        meal2.setFat(30);
        meal2.setCarbs(60);

        assertNotEquals("steak & potatoes", meal2.getMealName());
        assertNotEquals(650, meal2.getCalories());
        assertNotEquals(55, meal2.getProtein());
        assertNotEquals(10, meal2.getFat());
        assertNotEquals(45, meal2.getCarbs());
    }

    @Test
    public void userConstructorTest(){
        User user = new User("mario", "ppeach");

        assertFalse(user.isAdmin());
        assertEquals(user.getUsername(), "mario");
        assertEquals(user.getPassword(), "ppeach");
        assertNotEquals(user.getId(), 1);
    }

    @Test
    public void userSettersTest(){
        User user = new User("mario", "ppeach");
        user.setAdmin(true);
        user.setId(3);
        user.setPassword("pass");
        user.setUsername("luigi");

        assertTrue(user.isAdmin());
        assertEquals(user.getUsername(), "luigi");
        assertEquals(user.getPassword(), "pass");
        assertEquals(user.getId(), 3);
    }

    @Test
    public void IngredientConstructorTest() {
        Ingredients ingredients = new Ingredients(1, 1, 0, 1, 0, 1, 0);
        assertEquals(1, ingredients.getBeef());
        assertEquals(1, ingredients.getChicken());
        assertEquals(0, ingredients.getFish());
        assertEquals(1, ingredients.getRice());
        assertEquals(0, ingredients.getGreensA());
        assertEquals(1, ingredients.getGreensB());
        assertEquals(0, ingredients.getGreensC());


    }
    @Test
    public void IngredientsGettersandSettersTest(){
        Ingredients ing = new Ingredients();

        ing.setMealId(101);
        ing.setBeef(1);
        ing.setChicken(0);
        ing.setFish(1);
        ing.setRice(0);
        ing.setGreensA(1);
        ing.setGreensB(0);
        ing.setGreensC(1);

        assertEquals(101, ing.getMealId());
        assertEquals(1, ing.getBeef());
        assertEquals(0, ing.getChicken());
        assertEquals(1, ing.getFish());
        assertEquals(0, ing.getRice());
        assertEquals(1, ing.getGreensA());
        assertEquals(0, ing.getGreensB());
        assertEquals(1, ing.getGreensC());


    }
}