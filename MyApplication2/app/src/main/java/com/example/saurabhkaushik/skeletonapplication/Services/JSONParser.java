package com.example.saurabhkaushik.skeletonapplication.Services;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by saurabhkaushik on 03/12/16.
 */

public class JSONParser {
    JSONObject jsonObject = null;
    InputStream inputStream;
    public JSONParser() {}

    public String parseInputStream(InputStream inputStream) {
        StringBuilder sb = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String makeHttpRequest(String url) {
        String result = "";
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            inputStream = httpEntity.getContent();
            result = parseInputStream(inputStream);
        } catch (IOException e) {
            Log.e("JSONParser", "Error making Http request");
        }
        return result;
    }
}
