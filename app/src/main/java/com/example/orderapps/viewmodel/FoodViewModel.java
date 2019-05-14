package com.example.orderapps.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.orderapps.model.Food;
import com.example.orderapps.model.Transaction;
import com.example.orderapps.repository.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FoodViewModel extends ViewModel {

    private OrderRepository repo;

    public FoodViewModel(){
        repo = new OrderRepository();
    }

    public LiveData<List<Food>> getListFood(){
        MutableLiveData<List<Food>> result = new MutableLiveData<>();
        result.setValue(repo.getListFood());
        return result;
    }

    public boolean checkTransaction(Food food){
        return food.getAmount() > 0 ? true : false;
    }

    public int getTotalFood(Transaction transaction){
        int total = 0;
        for(Food food : transaction.getListFood()){
            total += food.getAmount();
        }
        return total;
    }

    public void addTransaction(Food food, int amount){
        Transaction transaction = Transaction.getInstance();
        int idx = transaction.getListFood().indexOf(food);
        if(idx == -1){
            // NOT FOUND
            food.setAmount(amount);
            transaction.getListFood().add(food);
        }
        else{
            // APPEND EXISTING
            transaction.getListFood().get(idx).setAmount(amount + food.getAmount());
        }
    }

    public String getDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        return format.format(date);
    }
}