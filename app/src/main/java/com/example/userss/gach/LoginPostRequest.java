package com.example.userss.gach;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class LoginPostRequest extends AsyncTask<JSONObject, Void, String> {
    Activity activity;
    URL url;
    Variable variable;


    public LoginPostRequest(Activity activity) {
        this.activity = activity;
    }


    @Override
    protected String doInBackground(JSONObject... postDataParams) {

        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(10000 /* milliseconds */);
            conn.setRequestMethod("POST");
//            String cookieString = variable.getCookies();
//            if (cookieString != null) {
//                conn.setRequestProperty("user", cookieString);
//            }
//            conn.setRequestProperty("Content-Type", "application/json");//application JSON 형식으로 전송
//            conn.setRequestProperty("Accept", "text/html");//서버에 response 데이터를 html로 받음
            conn.setDoInput(true);
            conn.setDoOutput(true);


            //서버로 보내기위해서 스트림 만듬
            OutputStream os = conn.getOutputStream();

            //버퍼를 생성하고 넣음
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            String str = getPostDataString(postDataParams[0]);

            Log.e("params", "Post String = " + str);

            writer.write(str);

            writer.flush();
            writer.close(); //버퍼를 받아줌
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                //서버로 부터 데이터를 받음

                final String COOKIES_HEADER = "Set_Cookie";


//                List<String> cookies = conn.getHeaderFields().get("set-cookie"); // 쿠키 값 조회방법
//
//                if (cookies != null) {
//                    for (String cookie : cookies) {
//                        Log.d("@COOKIE", cookie.split(";\\s*")[0]);
//                        String cok = cookie.split(";\\s*")[0];
//                        variable.setCookies(cok);
//                        Log.e("쿠키",variable.getCookies());
//                    }
//                }

                //Log.e("쿠키",variable.getCookies());


                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                Log.e("파람스", sb.toString());


                in.close();

//                if (!sb.toString().trim().contains("Success")) {
//                    Log.d("LoginPostRequest혜원", "되냐?");
//                    SbExtraction(sb); // 스트링버퍼를 추출해서 세팅해줌
//                }

                Log.d("LoginPostRequest혜원", "되냐?");
                SbExtraction(sb); // 스트링버퍼를 추출해서 세팅해줌

                return sb.toString(); //서버로 부터 받은 값을 리턴해줌 아마 OK!!가 들어올것임

            } else {
                return new String("Server Error : " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(activity, "서버가꺼져있습니다.",
                    Toast.LENGTH_LONG).show();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
//          Toast.makeText(activity, result,
//                Toast.LENGTH_LONG).show();
        super.onPostExecute(result);

        Log.e("로그5", result);
        if (result.contains("Successfully")) {

            new GetListData(activity).execute();   // 리스트 받아오기




            activity.finish();
        } else {
            Toast.makeText(activity, "로그인 실패",
                    Toast.LENGTH_LONG).show();

        }


    }

    private String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        // variable = Variable.getInstance();

        Iterator<String> itr = params.keys();


        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);


            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }


    private void SbExtraction(StringBuffer sb) {

        String SB = sb.toString(); //일단 String버퍼를 스트링 형식으로 변형

        try {
//            JSONArray jsonArray = new JSONArray(SB);
//            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            JSONObject jsonObject = new JSONObject(SB);

            Log.e("LoginPostRequest혜원", jsonObject.toString());
            Log.e("LoginPostRequest혜원", jsonObject.getJSONObject("data").toString());
            Log.e("LoginPostRequest혜원", jsonObject.getJSONObject("data").getString("user_id"));
            Log.e("LoginPostRequest혜원", jsonObject.getJSONObject("data").getString("user_pw"));
            Log.e("LoginPostRequest혜원", jsonObject.getJSONObject("data").getString("user_name"));

//            Log.e("LoginPostRequest혜원", jsonObject.getString("user_id"));

            Variable.getUser().setID(jsonObject.getJSONObject("data").getString("user_id"));
            Variable.getUser().setPw(jsonObject.getJSONObject("data").getString("user_pw"));
            Variable.getUser().setName(jsonObject.getJSONObject("data").getString("user_name"));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}