package com.uem.testapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

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

        StoreDAO storeDAO = modelsAppDatabase.getStoreDAO();
        Store store = new Store();
        store.address = "Новочеркасск";
        store.name = "Магазин";
        Store store1 = new Store();
        store1.address = "Достоевского 89ж";
        store1.name = "Ларек";
        Store store3 = new Store();
        store3.address = "Новочеркасск";
        store3.name = "Магазин";
        Store store4 = new Store();
        store4.address = "Достоевского 89ж";
        store4.name = "Ларек";
        Store store5 = new Store();
        store5.address = "Новочеркасск";
        store5.name = "Магазин";
        Store store6 = new Store();
        store6.address = "Достоевского 89ж";
        store6.name = "Ларек";
        Store store7 = new Store();
        store7.address = "Новочеркасск";
        store7.name = "Магазин";
        Store store8 = new Store();
        store8.address = "Достоевского 89ж";
        store8.name = "Ларек";
        storeDAO.addStore(store);
        storeArrayList.add(store);
        storeDAO.addStore(store1);
        storeArrayList.add(store1);
        storeDAO.addStore(store3);
        storeArrayList.add(store3);
        storeDAO.addStore(store4);
        storeArrayList.add(store4);
        storeDAO.addStore(store5);
        storeArrayList.add(store5);
        storeDAO.addStore(store6);
        storeArrayList.add(store6);
        storeDAO.addStore(store7);
        storeArrayList.add(store7);
        storeDAO.addStore(store8);
        storeArrayList.add(store8);


        adapter = new StoreAdapter(storeArrayList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}