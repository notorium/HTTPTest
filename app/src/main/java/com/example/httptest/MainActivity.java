package com.example.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private HttpRequest_GET httpreq; // 追加
    private HttpRequest_POST httpreq2;
    private EditText editText, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
    }

    public void getdata(View v) {
        httpreq = new HttpRequest_GET(this); // 追加
        httpreq.execute("http://fukuiohr2.sakura.ne.jp/2021/Zazen/test2.php"); // 追加
    }

    public void postdata(View v) {
        String str = "{\"id\":\"" + editText.getText().toString() +
                "\",\"name\":\"" + editText2.getText().toString() +
                "\",\"pass\":\"" + editText3.getText().toString() + "\"}";
        httpreq2 = new HttpRequest_POST(this, str);
        httpreq2.execute("http://fukuiohr2.sakura.ne.jp/2021/Zazen/test.php");
    }
}