package com.gyamfimartins.cashback.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gyamfimartins.cashback.R;
import com.gyamfimartins.cashback.adapter.ProductAdapter;
import com.gyamfimartins.cashback.model.Product;

import java.util.List;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView tvCurrent_Value, tvName;
    public ImageView imageView;

    public ProductViewHolder(@NonNull View itemView, ProductAdapter.OnItemClickListener listener, List<Product> products) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        tvCurrent_Value = itemView.findViewById(R.id.tvCurrent_Value);
        tvName = itemView.findViewById(R.id.tvName);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(products.get(position));
                }
            }
        });
    }


}
