package com.example.android_project_final;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.android_project_final.database.entities.Ingredients;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

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

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


}

