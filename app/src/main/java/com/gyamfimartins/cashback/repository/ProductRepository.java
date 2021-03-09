package com.gyamfimartins.cashback.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyamfimartins.cashback.model.Product;
import com.gyamfimartins.cashback.utils.loadJSONFromAsset;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public ProductRepository() {

    }

    public LiveData<List<Product>> getProduct(Context context) {
        MutableLiveData<List<Product>> data = new MutableLiveData<>();
        String jsonFileString = loadJSONFromAsset.getJsonFromAssets(context, "Offers.json");
        Gson gson = new Gson();
        Type listProductType = new TypeToken<List<Product>>() {
        }.getType();
        List<Product> productList = gson.fromJson(jsonFileString, listProductType);
        data.setValue(productList);
        return data;
    }
}
