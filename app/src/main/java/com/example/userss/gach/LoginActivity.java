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

public class LoginActivity extends AppCompatActivity {


    EditText IDEditText;
    EditText PWEditText;
    static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;

        IDEditText = findViewById(R.id.editTextId);
        PWEditText = findViewById(R.id.editTextPassword);


        // 로그인 화면 전환
        Button log = (Button)findViewById(R.id.buttonLogin);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = IDEditText.getText().toString();
                String pw = PWEditText.getText().toString();


                JSONObject postDataParam = new JSONObject();
                try {
                    postDataParam.put("id", id);
                    postDataParam.put("pw", pw);



                } catch (JSONException e) {
                    Log.e("LoginActivity혜원", "JSONEXception");
                }
                new LoginInsertData(LoginActivity.this).execute(postDataParam);

//                Intent intent = new Intent(
//                        getApplicationContext(), // 현재 화면의 제어권자
//                        MainActivity.class); // 다음 넘어갈 클래스 지정
//                startActivity(intent); // 다음 화면으로 넘어간다


            }
        });

        // 회원가입 화면 전환
        Button sign = (Button)findViewById(R.id.buttonSignin);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        SignupActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
    }
}
