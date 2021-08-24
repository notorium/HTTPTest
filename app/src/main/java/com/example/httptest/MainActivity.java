package com.example.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.httptest.R;

public class MainActivity extends AppCompatActivity {

    private AsyncHttpRequest httpreq; // 追加

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpreq = new AsyncHttpRequest(this); // 追加
        httpreq.execute("http://localhost:8000/Zazen/test.json"); // 追加

    }
}