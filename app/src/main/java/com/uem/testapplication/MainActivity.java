package com.uem.testapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView textStoreTitle;
    private EditText newStoreName, newStoreAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Торговые точки");

        textStoreTitle = findViewById(R.id.newStoreTitle);
        newStoreName = findViewById(R.id.newStoreName);
        newStoreAddress = findViewById(R.id.newStoreAddress);


        recyclerView = findViewById(R.id.recyclerView);
        modelsAppDatabase = Room.databaseBuilder(getApplicationContext(),
                ModelsAppDatabase.class, "database")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();

        storeArrayList.addAll(modelsAppDatabase.getStoreDAO().getAllStore());


        adapter = new StoreAdapter(storeArrayList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void addStore(View view) {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View addStore = layoutInflater.inflate(R.layout.add_store, null);

        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);

        mDialogBuilder.setView(addStore);

        final EditText userInputName = (EditText) addStore.findViewById(R.id.newStoreName);
        final EditText userInputAddress = (EditText) addStore.findViewById(R.id.newStoreAddress);

        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                if (userInputName.getText().toString().isEmpty()){
                                    Toast.makeText(MainActivity.this, "Введите название", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                if (userInputAddress.getText().toString().isEmpty()) {
                                    Toast.makeText(MainActivity.this, "Введите адрес", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                    createStore(userInputName.getText().toString(), userInputAddress.getText().toString());
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = mDialogBuilder.create();

        alertDialog.show();

    }

    private void createStore(String name, String address) {
        long id = modelsAppDatabase.getStoreDAO().addStore(new Store(0, name, address));

        Store store = modelsAppDatabase.getStoreDAO().getStore(id);

        storeArrayList.add(0, store);
        adapter.notifyDataSetChanged();
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

    public void activityProductList(View view) {
        startActivity(new Intent(MainActivity.this, ProductListActivity.class));
    }

}