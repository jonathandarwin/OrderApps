package com.example.orderapps.app;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orderapps.R;
import com.example.orderapps.adapter.FoodAdapter;
import com.example.orderapps.common.BaseActivity;
import com.example.orderapps.common.ClickUtil;
import com.example.orderapps.databinding.BottomDialogOrderBinding;
import com.example.orderapps.databinding.FoodActivityBinding;
import com.example.orderapps.databinding.OrderActivityBinding;
import com.example.orderapps.model.Food;
import com.example.orderapps.model.Transaction;
import com.example.orderapps.viewmodel.FoodViewModel;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends BaseActivity<FoodActivityBinding, FoodViewModel>
            implements ClickUtil{

    FoodAdapter adapter;
    List<Food> listFood;
    Transaction transaction;

    public FoodActivity(){
        super(FoodViewModel.class, R.layout.food_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listFood = new ArrayList<>();
        transaction = Transaction.getInstance();
        transaction.setDate(getViewModel().getDate());
        setAdapter();

        getViewModel().getListFood().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(@Nullable List<Food> foods) {
                listFood.clear();
                if(foods != null && foods.size() > 0){
                    listFood.addAll(foods);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void setAdapter(){
        adapter = new FoodAdapter(this, listFood, this);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(transaction.getListFood().size() > 0){
            showSnackbar();
        }
    }

    @Override
    public void onItemClick(final Food food) {
        final BottomDialogOrderBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.bottom_dialog_order, null, false);
        dialogBinding.setViewModel(food);

        final BottomSheetDialog bottomDialog = new BottomSheetDialog(this);
        bottomDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bottomDialog.setContentView(dialogBinding.getRoot());
        bottomDialog.show();

        dialogBinding.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });

        dialogBinding.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt(dialogBinding.txtAmount.getText().toString());
                if(amount > 0){
                    dialogBinding.txtAmount.setText(Integer.toString(--amount));
                }
            }
        });

        dialogBinding.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt(dialogBinding.txtAmount.getText().toString());
                dialogBinding.txtAmount.setText(Integer.toString(++amount));
            }
        });

        dialogBinding.btnAddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food.setAmount(Integer.parseInt(dialogBinding.txtAmount.getText().toString()));
                if(getViewModel().checkTransaction(food)){
                    transaction.getListFood().add(food);
                    showSnackbar();
                    bottomDialog.dismiss();
                }
                else{
                    Toast.makeText(FoodActivity.this, "Please order min. 1 food", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showSnackbar(){
        Snackbar snackbar = Snackbar.make(getBinding().llOrder, getViewModel().getTotalFood(transaction) + " item(s) in cart", Snackbar.LENGTH_INDEFINITE);
        View snackbarLayout = snackbar.getView();
        TextView text = (TextView) snackbarLayout.findViewById(android.support.design.R.id.snackbar_text);
        text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cart, 0, 0, 0);
        snackbar.setAction("Checkout", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoOrderIntent();
            }
        });
        snackbar.show();
    }

    private void gotoOrderIntent(){
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
}
