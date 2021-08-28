package com.example.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private HttpRequest_GET httpreq; // 追加
    private HttpRequest_POST httpreq2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpreq2 = new HttpRequest_POST(this, "");
        httpreq2.execute("");
    }

    public void getdata(View v) {
        httpreq = new HttpRequest_GET(this); // 追加
        httpreq.execute("http://localhost:8000/Zazen/test.json"); // 追加
    }

    public void postdata(View v) {
        httpreq2 = new HttpRequest_POST(this, "");
        httpreq2.execute("");
    }
}