package com.example.userss.gach;


import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

public class CartDeleteInsertData extends CartDeletePostRequest { //로그인할때 쓰는거 값을 받고 받아오는걸로 로그인함
    public CartDeleteInsertData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
//      EditText server = activity.findViewById(R.id.server);
        String serverURLStr = Variable.getServerURl();  // 서버 주소
        try {
            url = new URL(serverURLStr + "/cart/delete");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}