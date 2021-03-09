package com.gyamfimartins.cashback.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gyamfimartins.cashback.R;
import com.gyamfimartins.cashback.model.Product;
import com.gyamfimartins.cashback.viewholder.ProductViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> implements Filterable {
    private List<Product> productList;
    private List<Product> productListfull;
    private Context context;
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_row, parent, false);
        return new ProductViewHolder(view, listener, productList);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvCurrent_Value.setText(product.getCurrent_value());
        String imageurl = product.getUrl();
        Glide.with(context)
                .load(imageurl)
                .thumbnail(0.5f)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public void getProductList(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
        productListfull = new ArrayList<>(productList);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private final Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Product> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(productListfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Product item : productListfull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList.clear();
            productList.addAll((List) results.values);
            // refresh the list with filtered data
            notifyDataSetChanged();
        }
    };

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
