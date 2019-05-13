package com.example.orderapps.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.orderapps.R;
import com.example.orderapps.common.ClickUtil;
import com.example.orderapps.databinding.ListOrderItemBinding;
import com.example.orderapps.model.Food;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<Food> listFood;
    Context context;
    ClickUtil listener;

    public OrderAdapter(Context context, List<Food> listFood, ClickUtil listener){
        this.listFood = listFood;
        this.context = context;
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ListOrderItemBinding binding;
        public ViewHolder(ListOrderItemBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ListOrderItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_order_item, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Food food = listFood.get(i);
        viewHolder.binding.setViewModel(food);
        viewHolder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(food);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }
}
