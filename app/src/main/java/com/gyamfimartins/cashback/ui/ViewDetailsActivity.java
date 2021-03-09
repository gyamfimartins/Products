package com.gyamfimartins.cashback.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyamfimartins.cashback.R;

public class ViewDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        TextView tvname = findViewById(R.id.tvName);
        TextView tvCurrent_Value = findViewById(R.id.tvCurrent_Value);
        TextView tvdescription = findViewById(R.id.tvdescription);
        TextView tvterms = findViewById(R.id.tvterms);
        ImageView imageView = findViewById(R.id.imageView);

        String name = getIntent().getStringExtra("name");
        String url = getIntent().getStringExtra("url");
        String description = getIntent().getStringExtra("description");
        String current_value = getIntent().getStringExtra("current_value");
        String terms = getIntent().getStringExtra("terms");

        tvname.setText(name);
        tvdescription.setText(description);
        tvCurrent_Value.setText(current_value);
        tvterms.setText(terms);

        Glide.with(this)
                .load(url)
                .thumbnail(0.5f)
                .into(imageView);
    }
}