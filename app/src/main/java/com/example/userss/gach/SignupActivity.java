package com.example.userss.gach;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    EditText IDText;
    EditText PWText;
    EditText NameText;
    static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mContext = this;

        IDText = findViewById(R.id.signup_id);
        PWText = findViewById(R.id.signup_pw);
        NameText = findViewById(R.id.signup_name);


        // 로그인 화면 전환
        Button log = (Button)findViewById(R.id.buttonLogin);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userID = IDText.getText().toString();
                String userPassword = PWText.getText().toString();
                String userName = NameText.getText().toString();


                JSONObject postDataParam = new JSONObject();
                try {
                    postDataParam.put("userID", userID);
                    postDataParam.put("userPassword", userPassword);
                    postDataParam.put("userName", userName);
                } catch (JSONException e) {
                    Log.e("LoginActivity혜원", "JSONEXception");
                }
                new LoginInsertData(SignupActivity.this).execute(postDataParam);

//                Intent intent = new Intent(
//                        getApplicationContext(), // 현재 화면의 제어권자
//                        MainActivity.class); // 다음 넘어갈 클래스 지정
//                startActivity(intent); // 다음 화면으로 넘어간다


            }
        });


    }
}
