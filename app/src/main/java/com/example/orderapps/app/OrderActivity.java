package com.example.orderapps.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.orderapps.R;
import com.example.orderapps.adapter.OrderAdapter;
import com.example.orderapps.common.BaseActivity;
import com.example.orderapps.common.ClickUtil;
import com.example.orderapps.databinding.OrderActivityBinding;
import com.example.orderapps.model.Food;
import com.example.orderapps.model.Transaction;
import com.example.orderapps.viewmodel.OrderViewModel;

import java.util.ArrayList;

public class OrderActivity extends BaseActivity<OrderActivityBinding, OrderViewModel>
                implements View.OnClickListener, ClickUtil {

    OrderAdapter adapter;
    Transaction transaction;

    public OrderActivity(){
        super(OrderViewModel.class, R.layout.order_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        transaction = Transaction.getInstance();
        getBinding().setViewModel(transaction);
        getBinding().setTotal(getViewModel().countTotal());

        getBinding().close.setOnClickListener(this);
        getBinding().btnOrder.setOnClickListener(this);

        setAdapter();
    }

    private void setAdapter(){
        adapter = new OrderAdapter(this, transaction.getListFood(), this);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Food food) {
        int idx = getViewModel().deleteTransaction(food);
        adapter.notifyItemRemoved(idx);
        getBinding().setTotal(getViewModel().countTotal());
    }

    @Override
    public void onClick(View v) {
        if(v.equals(getBinding().close)){
            finish();
        } else if (v.equals(getBinding().btnOrder)) {
            if(transaction.getListFood().size() > 0){
                transaction.setListFood(new ArrayList<Food>());
                Toast.makeText(this, "Order Success", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Toast.makeText(this, "There are no order right now", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
