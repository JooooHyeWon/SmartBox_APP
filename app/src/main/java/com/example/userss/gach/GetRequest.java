package com.example.userss.gach;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

abstract public class GetRequest extends AsyncTask<String, Void, String> {
    final static String TAG = "AndroidNodeJS";
    Activity activity;
    URL url;
    public static StringBuffer output = null;

    public GetRequest(Activity activity) {
        this.activity = activity;
    }



    @Override
    protected String doInBackground(String... strings) {
        output = new StringBuffer();

        try {
            Log.d("TMapGetData", "겟데이터 두인백그라운드");
            if (url == null) {
                Log.e(TAG, "Error: URL is null ");
                return null;
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn == null) {
                Log.e(TAG, "HttpsURLConnection Error");
                return null;
            }

            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");



            conn.setDoInput(true);
            conn.setDoOutput(false);

            conn.setRequestProperty("user_id",Variable.getUser().getID());

            int resCode = conn.getResponseCode();

            if (resCode != HttpsURLConnection.HTTP_CREATED) {
                Log.e(TAG, "HttpsURLConnection ResponseCode: " + resCode);
                conn.disconnect();
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));


            String line = null;

            while (true) {
                line = reader.readLine();
                if (line == null) {
                    break;
                }
                output.append(line + "\n");
            }
            Log.d(TAG,output.toString());


            reader.close();
            conn.disconnect();

        } catch (IOException ex) {
            Log.e(TAG, "Exception in processing response.", ex);
            ex.printStackTrace();
        }


        Log.d(TAG,output.toString());
        return output.toString();
    }

}