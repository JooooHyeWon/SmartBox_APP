package com.example.userss.gach;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.VectorEnabledTintResources;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ShoppingActivity extends AppCompatActivity {

    private ListView ItemListView;  // 아이템을 띄우기 위한 커스텀 리스트뷰
    public static ItemAdapter itemAdapter;   // 어댑터 객체를 만들고
    public static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);


        ItemListView = findViewById(R.id.listView2);   // 리스트뷰 연결

        dataSetting();  // 데이터를 세팅하는 메소드(임의로 만든것)


        ItemListView.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                final String ItemName;

                ItemName = Variable.getCart().get(position).getList_name();
                builder.setTitle(Variable.getCart().get(position).getList_name());


                builder.setMessage("위 상품을 장바구니에서 삭제하겠습니까?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        count = position;

                        JSONObject postDataParam = new JSONObject();

                        try {
                            postDataParam.put("user_id", Variable.getUser().getID());
                            postDataParam.put("list_name",ItemName);
                        } catch (JSONException e) {
                            Log.e("ShoppingActivity혜원", "JSONEXception");
                        }

                        new CartDeleteInsertData(ShoppingActivity.this).execute(postDataParam);
                    }
                });

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ShoppingActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        }));



    }

    private void dataSetting() {   // 데이터를 세팅하는 메소드

        itemAdapter = new ItemAdapter();

        if(Variable.getCart().isEmpty()){
            return;
        } else{
            // favorite점수에 따라서 arraylist순서를 바꾸려면 여기서 바꾸는게 가장 좋아보임
            for (int i = 0; i < Variable.getCart().size(); i++) {     // item arraylist에 사이즈 만큼 반복
                itemAdapter.addItem
                        (Variable.getCart().get(i).getList_name(),
                                Variable.getCart().get(i).getList_favorite(),
                                Variable.getCart().get(i).getList_touch(),
                                Variable.getCart().get(i).getList_shake(),
                                Variable.getCart().get(i).getList_tryon(),
                                Variable.getCart().get(i).getList_photo()
                        );

                Log.d("ShoppingActivity혜원", String.valueOf(Variable.getCart().size()));
                Log.d("ShoppingActivity혜원", Variable.getCart().get(i).getList_name());
//            Log.d("MainActivity혜원", String.valueOf(Variable.getItem().get(i).getlist_favorite()));
                // itemadapter클래스의 additem메소드를 통해 itemadapter클래스 내의 arraylist에 item이 담겨있는 정보를저장하는부분
                // 이부분도 여러부분으로 구현할 수 있겠으나 인터넷에 떠도는 예제를 보고 수정안하고 걍 한 것이므로 참고할 것
            }

        }

        ItemListView.setAdapter(itemAdapter);  // 리스트뷰에 어댑터를 연결


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }



}
