package com.example.httptest;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.httptest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpRequest extends AsyncTask<String, Void, JSONObject> {
    private Activity mActivity;

    public AsyncHttpRequest(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        HttpURLConnection con = null;
        StringBuilder builder = new StringBuilder();
        JSONObject json = new JSONObject();
        try {
            URL url = new URL(params[0]);
            con = (HttpURLConnection) url.openConnection();
            InputStream stream = con.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null)
                builder.append(line);
            stream.close();

            json = new JSONObject(builder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally{
            con.disconnect();
        }

        return json;
    }

    public void onPostExecute(JSONObject json) {
        StringBuilder builder = new StringBuilder();
        try {
            JSONArray array = json.getJSONArray("profile");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                builder.append(obj.getString("no") + "\n");
                builder.append(obj.getString("name") + "\n");
                builder.append(obj.getJSONObject("address").getString("state"));
                builder.append(obj.getJSONObject("address").getString("city"));
                builder.append(obj.getJSONObject("address").getString("address1") + "\n");
                builder.append(obj.getString("phone") + "\n");
                builder.append(obj.getString("mail") + "\n");
            }
            ((TextView) mActivity.findViewById(R.id.textview)).setText(builder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}