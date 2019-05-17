package com.example.userss.gach;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetListData extends GetRequest {

    public GetListData(Activity activity) {
        super(activity);
    }


    @Override
    protected void onPreExecute() {
        String serverURLStr = Variable.getServerURl();  // 서버 주소
        try {
            url = new URL(serverURLStr + "/list/getlist");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPostExecute(String jsonString) {

        Log.d("GetListData혜원", jsonString);

        ArrayList<Item> arrayList = getItemArrayListFromJSONString(jsonString); //전체를 저장해야하니까

        Variable.setItem(arrayList);

        for (int i = 0; i < Variable.getItem().size(); i++) {
            Log.d("GetListData혜원", Variable.getItem().get(i).list_name);
            Log.d("GetListData혜원", Variable.getItem().get(i).list_favorite);
        }


//        Variable.setItem(getItemArrayListFromJSONString(jsonString)); 위 두줄과 동일


//        TempOfKiwi.setBikes(arrayList);  //
//
//
//
//        if(TempOfKiwi.flag1){
//            Intent Loginintent = new Intent(((LoginActivity) LoginActivity.mContext), NaviActivity.class);
//            ((LoginActivity) LoginActivity.mContext).startActivity(Loginintent);
//            activity.finish();   // 이거 액티비티를 가져오는거기 때문에 로그인 액티비티면 피니쉬를 안하고 인서트 코드 액티비티면 피니쉬해야함
//        }

    }

    protected boolean True() {
        return true;
    }

    protected ArrayList<Item> getItemArrayListFromJSONString(String jsonString) {

        Log.d("GetListData혜원", jsonString);

        ArrayList<Item> output = new ArrayList(); //User Array 생성

        try {


            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);




                Item Item = new Item
                        (jsonObject.getString("list_name"),
                                jsonObject.getString("list_favorite")
                        );


                output.add(Item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return output;
    }
}