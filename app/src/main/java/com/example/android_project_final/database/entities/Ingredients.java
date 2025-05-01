package com.example.android_project_final.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.android_project_final.database.ApplicationDatabase;

import java.util.Objects;

@Entity(tableName = ApplicationDatabase.INGREDIENTS_TABLE) // version: 1
public class Ingredients {
    @PrimaryKey(autoGenerate = true)
    private int mealId; // id that connects to this ingredients list to its meal in the meal table

    // 0 = false
    // 1 = true - meal has this ingredient
    private int beef;
    private int pork;
    private int chicken;
    private int fish;
    private int rice;
    private int bread;
    private int greensA;
    private int greensB;
    private int greensC;


    public Ingredients(int beef, int pork, int chicken, int fish, int rice, int bread, int greensA, int greensB, int greensC){
        this.beef = beef;
        this.pork = pork;
        this.chicken = chicken;
        this.fish = fish;
        this.rice = rice;
        this.bread = bread;
        this.greensA =greensA;
        this.greensB = greensB;
        this.greensC = greensC;
    }

    public Ingredients(){
        this.beef = 0;
        this.pork = 0;
        this.chicken = 0;
        this.fish = 0;
        this.rice = 0;
        this.bread = 0;
        this.greensA = 0;
        this.greensB = 0;
        this.greensC = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients that = (Ingredients) o;
        return getMealId() == that.getMealId() && getBeef() == that.getBeef() && getPork() == that.getPork() && getChicken() == that.getChicken() && getFish() == that.getFish() && getRice() == that.getRice() && getBread() == that.getBread() && getGreensA() == that.getGreensA() && getGreensB() == that.getGreensB() && getGreensC() == that.getGreensC();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMealId(), getBeef(), getPork(), getChicken(), getFish(), getRice(), getBread(), getGreensA(), getGreensB(), getGreensC());
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getBeef() {
        return beef;
    }

    public void setBeef(int beef) {
        this.beef = beef;
    }

    public int getPork() {
        return pork;
    }

    public void setPork(int pork) {
        this.pork = pork;
    }

    public int getChicken() {
        return chicken;
    }

    public void setChicken(int chicken) {
        this.chicken = chicken;
    }

    public int getFish() {
        return fish;
    }

    public void setFish(int fish) {
        this.fish = fish;
    }

    public int getRice() {
        return rice;
    }

    public void setRice(int rice) {
        this.rice = rice;
    }

    public int getBread() {
        return bread;
    }

    public void setBread(int bread) {
        this.bread = bread;
    }

    public int getGreensA() {
        return greensA;
    }

    public void setGreensA(int greensA) {
        this.greensA = greensA;
    }

    public int getGreensB() {
        return greensB;
    }

    public void setGreensB(int greensB) {
        this.greensB = greensB;
    }

    public int getGreensC() {
        return greensC;
    }

    public void setGreensC(int greensC) {
        this.greensC = greensC;
    }
}
