package com.example.android_project_final.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.android_project_final.MainActivity;
import com.example.android_project_final.database.entities.Meal;
import com.example.android_project_final.database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Meal.class, User.class}, version = 1, exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase{
    public static final String USER_TABLE = "usertable";
    public static final String MEAL_TABLE = "mealtable";
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
                mDAO.deleteAll();
                dao.deleteAll();
                User admin = new User("admin1", "admin1");
                admin.setAdmin(true);
                dao.insert(admin);
                User testUser1 = new User("testUser1", "testUser1");
                dao.insert(testUser1);
                Meal meal = new Meal("pancakes", 5, 190,3,5,20,26);
                mDAO.insert(meal);
                meal = new Meal("cheeseburger", 9, 470,15,14,30,71);
                mDAO.insert(meal);
                meal = new Meal("Honey Garlic Chicken", 15, 565,58,18,43,191);
                mDAO.insert(meal);
            });
        }
    };

    public abstract UserDAO userDAO();
    public abstract MealDAO mealDAO();
}
