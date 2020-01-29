package com.example.weatherapp;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIUtils extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... address) {

        try {

            //connect to API
            URL url = new URL(address[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();

            //fetch data from API
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            //return String with all the data
            int data = isr.read();
            String content = "";
            char ch;

            while (data != -1){
                ch = (char)data;
                content = content + ch;
                data = isr.read();
            }

            return content;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
