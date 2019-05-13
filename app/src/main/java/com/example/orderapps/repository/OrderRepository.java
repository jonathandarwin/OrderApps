package com.example.orderapps.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.orderapps.model.Food;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    public List<Food> getListFood(){
        List<Food> listFood = new ArrayList<>();
        DatabaseFood databaseFood = new DatabaseFood();
        listFood = databaseFood.get();
        return listFood;
    }
}
