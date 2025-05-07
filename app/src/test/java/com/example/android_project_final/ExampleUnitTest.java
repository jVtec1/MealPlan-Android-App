package com.example.android_project_final;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.android_project_final.database.entities.Meal;

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

}