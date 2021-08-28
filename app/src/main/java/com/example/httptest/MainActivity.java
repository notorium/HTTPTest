package com.example.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private HttpRequest_GET httpreq; // 追加
    private HttpRequest_POST httpreq2;
    private EditText editText;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
    }

    public void getdata(View v) {
        httpreq = new HttpRequest_GET(this); // 追加
        httpreq.execute("http://localhost:8000/Zazen/test.json"); // 追加
    }

    public void postdata(View v) {
        String str = "{\"val1\":\"" + editText.getText().toString() +
                "\",\"val2\":\"" + editText2.getText().toString() + "\"}";
        httpreq2 = new HttpRequest_POST(this, str);
        httpreq2.execute("http://localhost:8000/Zazen/test.php");
    }
}