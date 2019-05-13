package com.example.orderapps.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.orderapps.BR;

import java.io.Serializable;
import java.util.Observable;

public class Food extends BaseObservable implements Serializable {
    protected String name;
    protected String description;
    protected int price;
    protected int amount;

    public Food(String name, String description, int price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Food(){

    }

    @Bindable
    public String getName() {
        return name;
    }

    public Food setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
        return this;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public Food setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
        return this;
    }

    @Bindable
    public int getPrice() {
        return price;
    }

    public Food setPrice(int price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
        return this;
    }

    @Bindable
    public String getDisplayPrice(){
        return "Rp. " + Integer.toString(price);
    }

    @Bindable
    public int getAmount() {
        return amount;
    }

    public Food setAmount(int amount) {
        this.amount = amount;
        notifyPropertyChanged(BR.amount);
        return this;
    }

    @Bindable
    public String getDisplayAmount(){
        return "x" + Integer.toString(amount);
    }

    @Bindable
    public String getDisplayTotal(){
        return "Rp. " + amount * price;
    }
}
