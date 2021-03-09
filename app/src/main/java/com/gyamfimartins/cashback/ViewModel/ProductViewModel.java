package com.gyamfimartins.cashback.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gyamfimartins.cashback.model.Product;
import com.gyamfimartins.cashback.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    ProductRepository productRepository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository();
    }

    public LiveData<List<Product>> getAllProducts() {
        return productRepository.getProduct(getApplication());
    }
}
