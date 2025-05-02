package com.example.android_project_final.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.android_project_final.MainActivity;
import com.example.android_project_final.database.entities.Ingredients;
import com.example.android_project_final.database.entities.Meal;
import com.example.android_project_final.database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Meal.class, Ingredients.class, User.class}, version = 1, exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase{
    public static final String USER_TABLE = "usertable";
    public static final String MEAL_TABLE = "mealtable";
    public static final String INGREDIENTS_TABLE = "ingredientstable";
    private static final String DATABASE_NAME = "ApplicationDatabase";
    private static volatile ApplicationDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static ApplicationDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (ApplicationDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    ApplicationDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE CREATED!");
            databaseWriteExecutor.execute(() ->{
                UserDAO dao = INSTANCE.userDAO();
                MealDAO mDAO = INSTANCE.mealDAO();
                IngredientsDAO iDAO = INSTANCE.ingredientsDAO();
                iDAO.deleteAll();
                mDAO.deleteAll();
                dao.deleteAll();
                // initial users
                User admin = new User("admin1", "admin1");
                admin.setAdmin(true);
                dao.insert(admin);
                User testUser1 = new User("testUser1", "testUser1");
                dao.insert(testUser1);
                // initial meals and connected ingredients list
                Meal meal = new Meal("Caesar Salad", 5.0, 90,9,5,9,0);
                mDAO.insert(meal);
                Ingredients ingredients = new Ingredients(0, 1, 0, 0, 1, 1, 0);
                iDAO.insert(ingredients);
                meal = new Meal("Sushi", 10.0, 159,9,7,18,0);
                mDAO.insert(meal);
                ingredients = new Ingredients(0, 0, 1, 1, 1, 0, 1);
                iDAO.insert(ingredients);
                meal = new Meal("Honey Garlic Chicken", 15.0, 565,58,18,43,0);
                mDAO.insert(meal);
                ingredients = new Ingredients(0, 1, 0, 1, 1, 1, 1);
                iDAO.insert(ingredients);
            });
        }
    };

    public abstract UserDAO userDAO();
    public abstract MealDAO mealDAO();
    public abstract IngredientsDAO ingredientsDAO();
}
