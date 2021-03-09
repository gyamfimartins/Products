package com.gyamfimartins.cashback.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;

import com.gyamfimartins.cashback.R;
import com.gyamfimartins.cashback.ViewModel.ProductViewModel;
import com.gyamfimartins.cashback.adapter.ProductAdapter;
import com.gyamfimartins.cashback.model.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvproduct;
    private ProductViewModel productViewModel;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvproduct = findViewById(R.id.rvproduct);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvproduct.setLayoutManager(layoutManager);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productAdapter = new ProductAdapter();

        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(MainActivity.this, ViewDetailsActivity.class);
                intent.putExtra("name", product.getName());
                intent.putExtra("url", product.getUrl());
                intent.putExtra("description", product.getDescription());
                intent.putExtra("terms", product.getTerms());
                intent.putExtra("current_value", product.getCurrent_value());
                startActivity(intent);
            }
        });

        getProducts();
    }


    private void getProducts() {
        productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                productAdapter.getProductList(productList, getApplication());
                rvproduct.setAdapter(productAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        //Initialize SearchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


}