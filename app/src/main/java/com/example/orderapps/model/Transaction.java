package com.example.orderapps.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.orderapps.BR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transaction extends BaseObservable implements Serializable {
    protected static Transaction instance;
    protected List<Food> listFood;
    protected String date;

    public static Transaction getInstance(){
        if(instance == null){
            instance = new Transaction();
        }
        return instance;
    }

    public Transaction(){
        listFood = new ArrayList<>();
    }

    @Bindable
    public List<Food> getListFood() {
        return listFood;
    }

    public Transaction setListFood(List<Food> listFood) {
        this.listFood = listFood;
        notifyPropertyChanged(BR.listFood);
        return this;
    }

    @Bindable
    public String getDate() {
        return date;
    }

    public Transaction setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
        return this;
    }
}
