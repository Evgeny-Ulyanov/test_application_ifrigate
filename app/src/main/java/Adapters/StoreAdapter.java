package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uem.testapplication.InfoActivity;
import com.uem.testapplication.MainActivity;
import com.uem.testapplication.R;

import java.util.ArrayList;

import Models.Store;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.MyViewHolder> {

    private ArrayList<Store> arrayList;
    Context context;

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textName;
        public TextView textAddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textName = itemView.findViewById(R.id.textName);
            textAddress = itemView.findViewById(R.id.textAddress);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Store store = arrayList.get(position);

            Intent intent = new Intent(context, InfoActivity.class);
            intent.putExtra("name", store.getName());
            intent.putExtra("address", store.getAddress());
            context.startActivity(intent);
        }
    }

    public StoreAdapter(ArrayList<Store> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
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
