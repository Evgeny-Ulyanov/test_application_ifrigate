package com.uem.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        setTitle("Совершить заказ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // кнопка назад в ActionBar

        TextView textViewNameInfo = findViewById(R.id.textViewNameInfo);
        TextView textViewAddressInfo = findViewById(R.id.textViewAddressInfo);

        Intent intent = getIntent(); // принимаем данные из интента
        if (intent != null) {

            textViewNameInfo.setText(intent.getStringExtra("name"));
            textViewAddressInfo.setText(intent.getStringExtra("address"));

        }
    }

    public void addOrder(View view) { // всплывающее сообщение
        Toast.makeText(this, "Создание заказа временно невозможно.", Toast.LENGTH_SHORT).show();
    }
}