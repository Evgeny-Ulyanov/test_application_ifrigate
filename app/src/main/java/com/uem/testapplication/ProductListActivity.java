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
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import Adapters.ProductListAdapter;
import Adapters.StoreAdapter;
import Data.ModelsAppDatabase;
import Models.ProductList;
import Models.Store;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ProductList> productArrayList = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ModelsAppDatabase modelsAppDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        setTitle("Список товаров");

        recyclerView = findViewById(R.id.recyclerViewProduct);
        modelsAppDatabase = Room.databaseBuilder(getApplicationContext(),
                ModelsAppDatabase.class, "database")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();

        productArrayList.addAll(modelsAppDatabase.getProductListDAO().getAllProduct());

        adapter = new ProductListAdapter(productArrayList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void activityMain(View view) {
        startActivity(new Intent(ProductListActivity.this, MainActivity.class));
    }

    public void addProduct(View view) {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View addProduct = layoutInflater.inflate(R.layout.add_product, null);

        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);

        mDialogBuilder.setView(addProduct);

        final EditText userInputName = (EditText) addProduct.findViewById(R.id.newproductName);
        final EditText userInputPrice = (EditText) addProduct.findViewById(R.id.newProductPrice);
        final EditText userInputUnit = (EditText) addProduct.findViewById(R.id.newProductUnit);

        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                if (userInputName.getText().toString().isEmpty()){
                                    Toast.makeText(ProductListActivity.this, "Введите название", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                if (userInputPrice.getText().toString().isEmpty()) {
                                    Toast.makeText(ProductListActivity.this, "Введите стоимость", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                if (userInputUnit.getText().toString().isEmpty()) {
                                    Toast.makeText(ProductListActivity.this, "Введите единицы измерения", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                createProduct(userInputName.getText().toString(), userInputPrice.getText().toString(), userInputUnit.getText().toString());
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

    private void createProduct(String name, String price, String unit) {
        long id = modelsAppDatabase.getProductListDAO().addProduct(new ProductList(0, name, price, unit));

        ProductList productList = modelsAppDatabase.getProductListDAO().getProduct(id);

        productArrayList.add(0, productList);
        adapter.notifyDataSetChanged();
    }

    private void deleteStore(Store store, int position) {
        productArrayList.remove(position);
        modelsAppDatabase.getStoreDAO().deleteStore(store);
        adapter.notifyDataSetChanged();
    }

    private void updateStore(String name, String price, String unit, int position) {
        ProductList productList= productArrayList.get(position);
        productList.setName(name);
        productList.setPrice(price);
        productList.setUnits(unit);


        modelsAppDatabase.getProductListDAO().updateProduct(productList);

        productArrayList.set(position, productList);

        adapter.notifyDataSetChanged();
    }
}