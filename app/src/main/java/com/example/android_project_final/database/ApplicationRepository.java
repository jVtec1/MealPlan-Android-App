package com.example.android_project_final.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.android_project_final.MainActivity;
import com.example.android_project_final.database.entities.Ingredients;
import com.example.android_project_final.database.entities.Meal;
import com.example.android_project_final.database.entities.User;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class ApplicationRepository {
    private final UserDAO userDAO;
    private final MealDAO mealDAO;
    private final IngredientsDAO ingredientsDAO;
    private ArrayList<Meal> allMeals;

    private static ApplicationRepository repository;

    private ApplicationRepository(Application application){
        ApplicationDatabase db = ApplicationDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
        this.mealDAO = db.mealDAO();
        this.ingredientsDAO = db.ingredientsDAO();
        this.allMeals = (ArrayList<Meal>) this.mealDAO.getAllMeals();
    }

    public static ApplicationRepository getRepository(Application application){
        if(repository != null){
            return repository;
        }
        Future<ApplicationRepository> future = ApplicationDatabase.databaseWriteExecutor.submit(
                new Callable<ApplicationRepository>() {
                    @Override
                    public ApplicationRepository call() throws Exception {
                        return new ApplicationRepository(application);
                    }
                }
        );
        try{
            return future.get();
        }catch (InterruptedException | ExecutionException e){
            Log.d(MainActivity.TAG, "Problem getting Application. thread error");
        }
        return null;
    }

    public void insertUser(User... user){
        ApplicationDatabase.databaseWriteExecutor.execute(() ->{
            userDAO.insert(user);
        });
    }

    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }

    public LiveData<Meal> getMealByName(String name) {
        return mealDAO.getMealByName(name);
    }

    public LiveData<Ingredients> getIngredientsByMealId(int mealId){
        return ingredientsDAO.getIngredientsByMealId(mealId);
    }


}
