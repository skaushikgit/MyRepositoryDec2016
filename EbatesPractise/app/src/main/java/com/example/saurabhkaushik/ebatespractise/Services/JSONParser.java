package com.example.saurabhkaushik.ebatespractise.Services;

import android.provider.Settings;
import android.util.Log;
import android.util.StringBuilderPrinter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by saurabhkaushik on 01/12/16.
 */
public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

    public JSONParser() {}

    public JSONObject makeHttpRequest(String url, String method) throws IOException {
        try{
            if (method == "POST") {

            } else if (method == "GET") {
                DefaultHttpClient httpClient = new DefaultHttpClient();
//                String paramString = URLEncodedUtils.format(params, "utf-8");
//                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }
        } catch (Exception ex) {
            Log.d("Networking", ex.getLocalizedMessage());
            throw new IOException("Error connecting");
        }

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line +"\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("JSON Parser", "Error parsing data" + e.toString());
        }

        try{
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data" +e.toString());
        }
        return jObj;
    }
}
