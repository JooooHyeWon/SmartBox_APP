package com.example.userss.gach;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    //view Objects
    private Button buttonScan;
    private TextView textViewName, textViewResult;
    private ListView ItemListView;  // 아이템을 띄우기 위한 커스텀 리스트뷰
    //qr code scanner object
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemListView = findViewById(R.id.listView);   // 리스트뷰 연결
        dataSetting();  // 데이터를 세팅하는 메소드(임의로 만든것)


//        // 데이터 원본 준비
//        String[] items = {"1위. 청자켓", "2위. 플라워원피스", "3위. 화이트 롱스커트"};
//
//        //어댑터 준비 (배열 객체 이용, simple_list_item_1 리소스 사용
//        ArrayAdapter<String> adapt
//                = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                items);
//
//        //어댑터 연결
//        ListView list = (ListView) findViewById(R.id.listView);
//        list.setAdapter(adapt);

        // 테스트용 qr화면 이동
//        Button my = (Button)findViewById(R.id.mytest);
//        my.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(
//                        getApplicationContext(), // 현재 화면의 제어권자
//                        QRActivity.class); // 다음 넘어갈 클래스 지정
//                startActivity(intent); // 다음 화면으로 넘어간다
//            }
//        });

        // 장바구니 화면 전환
        Button shopping = (Button) findViewById(R.id.shoppingButton);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        ShoppingActivity.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });


        // QR 인식
        //View Objects
        buttonScan = (Button) findViewById(R.id.qrButton);

        //intializing scan object
        qrScan = new IntentIntegrator(this);

        //button onClick
        buttonScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //scan option
                qrScan.setPrompt("Scanning...");
                //qrScan.setOrientationLocked(false);
                qrScan.initiateScan();
            }
        });
    }

    private void dataSetting() {   // 데이터를 세팅하는 메소드
        ItemAdapter itemAdapter = new ItemAdapter();   // 어댑터 객체를 만들고
        Item ChangeItem;

        for (int i = 0; i < Variable.getItem().size(); i++) {     // 호감도 수치에 따라 순서 바꿈
            for (int j = 0; j < Variable.getItem().size(); j++) {
                if (i == j || j < i) {  // 같은칸은 비교하지 않음

                } else {
                    if (Variable.getItem().get(i).list_favorite < Variable.getItem().get(j).list_favorite) {

                        Log.d("MainActivity혜원1", String.valueOf(Variable.getItem().get(i).getlist_favorite()));
                        Log.d("MainActivity혜원1", String.valueOf(Variable.getItem().get(j).getlist_favorite()));

                        //
                        ChangeItem = Variable.getItem().get(j);  // 호감도가 더 큰 아이템을 저장
                        Variable.getItem().set(j, Variable.getItem().get(i));  // 호감도가 더 큰 아이템을 앞쪽에 배치
                        Variable.getItem().set(i, ChangeItem);  // 호감도가 더 작은 아이템의 위치와 자리체인지
                    }
                }
            }
        }

        // favorite점수에 따라서 arraylist순서를 바꾸려면 여기서 바꾸는게 가장 좋아보임
        for (int i = 0; i < Variable.getItem().size(); i++) {     // item arraylist에 사이즈 만큼 반복
            itemAdapter.addItem(Variable.getItem().get(i).list_name, Variable.getItem().get(i).list_favorite);
            Log.d("MainActivity혜원", String.valueOf(Variable.getItem().get(i).getlist_name()));
            Log.d("MainActivity혜원", String.valueOf(Variable.getItem().get(i).getlist_favorite()));
            // itemadapter클래스의 additem메소드를 통해 itemadapter클래스 내의 arraylist에 item이 담겨있는 정보를저장하는부분
            // 이부분도 여러부분으로 구현할 수 있겠으나 인터넷에 떠도는 예제를 보고 수정안하고 걍 한 것이므로 참고할 것
        }

        ItemListView.setAdapter(itemAdapter);  // 리스트뷰에 어댑터를 연결


    }

    class AscendingString implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return b.compareTo(a);
        }
    }

    // QR코드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(MainActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                //qrcode 결과가 있으면
                Toast.makeText(MainActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
                    JSONObject obj = new JSONObject(result.getContents());
                    // 스캔한 상품 이름만 toast 출력
                    //Toast.makeText(MainActivity.this, obj.getString("name"), Toast.LENGTH_LONG).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(obj.getString("name"));
                    builder.setMessage("위 상품을 장바구니에 담으시겠습니까?");
                    builder.setIcon(android.R.drawable.ic_dialog_alert);
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "장바구니에 담겼습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    textViewResult.setText(result.getContents());
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}