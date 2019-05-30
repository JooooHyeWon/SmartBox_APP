package com.example.userss.gach;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetCartData extends GetRequest {

    public GetCartData(Activity activity) {
        super(activity);
    }


    @Override
    protected void onPreExecute() {
        String serverURLStr = Variable.getServerURl();  // 서버 주소
        try {
            url = new URL(serverURLStr + "/cart/getcart?" + "user_id=" + Variable.getUser().getID());
            Log.d("GetCartData혜원", Variable.getUser().getID());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPostExecute(String jsonString) {

        Log.d("GetCartData혜원", jsonString);

        Variable.setCart(getItemArrayListFromJSONString(jsonString));

//        Log.d("GetCartData혜원", Variable.getCart().get(0).getList_name());

        Intent GoToShopping = new Intent((activity), ShoppingActivity.class); //메인액티비티로 보내는 인텐트
        (activity).startActivity(GoToShopping);

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

        Log.d("GetCartData혜원", jsonString);

        ArrayList<Item> output = new ArrayList(); //User Array 생성

        try {

            JSONObject jsonObject = new JSONObject(jsonString);    // 파싱해서 받은 jsonString을 JsonObject로 변환
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");   // 이 JsonObject에서 message라는 아이디를 갖는 JsonArray를 추출

//            JSONArray jsonArray = new JSONArray(jsonString);

            Log.d("GetCartData혜원", String.valueOf(Variable.getItem().size()));
            Log.d("GetCartData혜원", String.valueOf(jsonArray.length()));


            for (int j = 0; j < Variable.getItem().size(); j++) {
                for (int i = 0; i < jsonArray.length(); i++) {   //jsonarray의 길이 만큼 반복
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);     // 추출한json어레이를 제이슨 오브젝트로 변환
                    if (Variable.getItem().get(j).getList_name().equals(jsonObject1.getString("list_name"))) {
                        output.add(Variable.getItem().get(j));
                    }
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            return output;
        }


        return output;
    }
}