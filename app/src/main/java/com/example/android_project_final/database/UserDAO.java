package com.example.android_project_final.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android_project_final.database.entities.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * from " + ApplicationDatabase.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUserName(String username);

    @Query("SELECT * from " + ApplicationDatabase.USER_TABLE + " WHERE id == :userId")
    LiveData<User> getUserByUserId(int userId);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + ApplicationDatabase.USER_TABLE + " ORDER BY id")
    List<User> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Query("DELETE from " + ApplicationDatabase.USER_TABLE)
    void deleteAll();

    @Query("DELETE from " + ApplicationDatabase.USER_TABLE + " WHERE id = :userId")
    void deleteUserById(int userId);
}
