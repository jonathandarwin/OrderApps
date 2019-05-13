package com.example.orderapps.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.orderapps.model.Food;
import com.example.orderapps.model.Transaction;

public class OrderViewModel extends ViewModel {

    public int deleteTransaction(Food food){
        Transaction transaction = Transaction.getInstance();
        int idx = transaction.getListFood().indexOf(food);
        transaction.getListFood().remove(food);
        return idx;
    }

    public String countTotal(){
        int total = 0;
        Transaction transaction = Transaction.getInstance();
        for (Food food : transaction.getListFood()){
            total += (food.getAmount() * food.getPrice());
        }
        return "Rp. " + Integer.toString(total);
    }
}
