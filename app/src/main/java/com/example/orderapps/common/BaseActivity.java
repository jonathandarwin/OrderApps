package com.example.orderapps.common;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity<DataBinding extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    private DataBinding binding;
    private VM viewModel;
    private Class<VM> vm;
    private int layout;

    public BaseActivity(){

    }

    public BaseActivity(Class<VM> vm, int layout){
        this.vm = vm;
        this.layout = layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.binding = DataBindingUtil.setContentView(this, layout);
        this.viewModel = ViewModelProviders.of(this).get(vm);
    }

    protected VM getViewModel(){
        return viewModel;
    }

    protected DataBinding getBinding(){
        return binding;
    }
}
