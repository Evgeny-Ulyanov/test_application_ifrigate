package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uem.testapplication.R;

import java.util.ArrayList;

import Models.ProductList;
import Models.Store;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {

    private ArrayList<ProductList> arrayList;

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        public TextView textProductName;
        public TextView textProductCost;
        public TextView textProductUnit;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textProductName = itemView.findViewById(R.id.textNameProduct);
            textProductCost = itemView.findViewById(R.id.textCostProduct);
            textProductUnit = itemView.findViewById(R.id.textProductUnits);
        }
    }

    public ProductListAdapter(ArrayList<ProductList> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,
                parent,false);
        ProductViewHolder myViewHolder = new ProductViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        ProductList productList = arrayList.get(position);

        holder.textProductName.setText(productList.getName());
        holder.textProductCost.setText(productList.getPrice());
        holder.textProductUnit.setText(productList.getUnits());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
