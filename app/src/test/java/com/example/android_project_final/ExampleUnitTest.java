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
        Meal meal = new Meal("Chicken Bowl", 500, 35, 45, 15);

        assertEquals("chicken bowl", meal.getMealName());
        assertEquals(500, meal.getCalories());
        assertEquals(35, meal.getProtein());
    }
}