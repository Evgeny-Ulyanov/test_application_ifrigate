package Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uem.testapplication.R;

import java.util.ArrayList;

import Models.Store;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {

    private ArrayList<Store> arrayList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView textName;
        public TextView textAddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textAddress = itemView.findViewById(R.id.textAddress);
        }
    }

    public StoreAdapter(ArrayList<Store> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,
                parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Store store = arrayList.get(position);

        holder.textName.setText(store.getName());
        holder.textAddress.setText(store.getAddress());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
