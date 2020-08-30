package com.uem.testapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import Adapters.StoreAdapter;
import Data.ModelsAppDatabase;
import Data.StoreDAO;
import Models.Store;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Store> storeArrayList = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ModelsAppDatabase modelsAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Торговые точки");


        recyclerView = findViewById(R.id.recyclerView);
        modelsAppDatabase = Room.databaseBuilder(getApplicationContext(),
                ModelsAppDatabase.class, "database")
                .allowMainThreadQueries().build();

        storeArrayList.addAll(modelsAppDatabase.getStoreDAO().getAllStore());


        adapter = new StoreAdapter(storeArrayList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void createStore(String name, String address) {
        long id = modelsAppDatabase.getStoreDAO().addStore(new Store(0, name, address));

        Store store = modelsAppDatabase.getStoreDAO().getStore(id);

        storeArrayList.add(0, store);
        adapter.notifyDataSetChanged();
    }

    public void addStore(View view) {
        deleteStore( storeArrayList.get(0), 0);
    }

    private void deleteStore(Store store, int position) {
        storeArrayList.remove(position);
        modelsAppDatabase.getStoreDAO().deleteStore(store);
        adapter.notifyDataSetChanged();
    }

    private void updateStore(String name, String address, int position) {
        Store store = storeArrayList.get(position);
        store.setName(name);
        store.setAddress(address);

        modelsAppDatabase.getStoreDAO().updateStore(store);

        storeArrayList.set(position, store);

        adapter.notifyDataSetChanged();
    }
}