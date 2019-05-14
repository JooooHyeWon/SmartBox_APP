package com.example.userss.gach;


import android.app.Activity;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginInsertData extends LoginPostRequest { //로그인할때 쓰는거 값을 받고 받아오는걸로 로그인함
    public LoginInsertData(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPreExecute() {
//      EditText server = activity.findViewById(R.id.server);
        String serverURLStr = Variable.getServerURl();  //민영이 서버
        try {
            url = new URL(serverURLStr + "/user/signin");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}