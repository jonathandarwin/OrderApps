package com.example.orderapps.repository;

import com.example.orderapps.model.Food;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFood {

    List<Food> listFood;

    public DatabaseFood(){
        listFood = new ArrayList<>();
        listFood.add(new Food("Super Family", "3 Rices, 3 Drinks, 5 Chickens", 98000));
        listFood.add(new Food("Blackpepper Chicken Rice", "Rice with chicken blackpepper and", 35000));
        listFood.add(new Food("Thai Chic", "Crispy chicken fillet served with Thai sauce.", 24000));
        listFood.add(new Food("Chic Melted Cheese", "Crispy boneless chicken served with cheese sauce on top.", 26000));
        listFood.add(new Food("Mixed Grill", "Grilled chic breast, beef sausages, and beef fillet serve with fries, mixed vegetable and our special sauce.", 32000));
        listFood.add(new Food("Steak Combo", "Grilled beef fillet and crispy chic Maryland served with our choices sauce.", 30000));
        listFood.add(new Food("Chic Teriyaki", "Saute shic fillet and sliced onion marinated with Teriyaki sauce served with rice.", 23000));
        listFood.add(new Food("French Fries", "Our fresh french fries", 20000));
        listFood.add(new Food("Onion Ring", "Crispy onion ring served with tartar sauce", 20000));
        listFood.add(new Food("Spaghetti Chic Black Pepper", "Spaghetti served with blackpepper chicken", 25000));
        listFood.add(new Food("Spaghetti Spicy Tuna", "Spaghetti served with spicy tuna sauce on top.", 27000));
        listFood.add(new Food("Spaghetti Bolognaise", "Spaghetti served with bolognaise sauce top with cheese", 27000));
    }

    public List<Food> get(){
        return listFood;
    }
}
