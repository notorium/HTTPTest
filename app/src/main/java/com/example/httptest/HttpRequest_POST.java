package com.example.httptest;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.httptest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest_POST extends AsyncTask<String, Void, JSONObject> {
    private Activity mActivity;
    private String str;

    public HttpRequest_POST(Activity activity, String string) {
        mActivity = activity;
        str = string;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        HttpURLConnection con = null;
        StringBuilder builder = new StringBuilder();
        JSONObject json = new JSONObject();
        try {
            URL url = new URL(params[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setConnectTimeout(5000);
            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            con.connect();
//            con.setRequestProperty("Content-Length", String.valueOf(str.length()));
            System.out.println(con);
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
//            writer.write(str);
//            writer.close();
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(str);
            out.flush();

            System.out.println(str);

            InputStream stream = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null)
                builder.append(line);
            stream.close();
            System.out.println(line);
            json = new JSONObject(builder.toString());

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            con.disconnect();
        }

        return json;
    }

    public void onPostExecute(JSONObject json) {
        StringBuilder builder = new StringBuilder();
        try {
            JSONObject array = json.getJSONObject("result");
            builder.append(array.getString("id") + "\n");
            builder.append(array.getString("name") + "\n");
            builder.append(array.getString("pass") + "\n");
        } catch (JSONException e) {
            e.printStackTrace();
            builder.append("");
        }
        System.out.println(json);
        ((TextView) mActivity.findViewById(R.id.textview)).setText(builder.toString());
    }
}