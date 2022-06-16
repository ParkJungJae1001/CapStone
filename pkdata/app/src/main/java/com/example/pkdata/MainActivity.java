package com.example.pkdata;



import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    Button save, read;
    EditText lat, name, lng, id,com;
    TextView data;

    int i = 9; //pk

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance("https://pkdata-8b1dd-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference(); //DatabaseReference의 인스턴스


        save = findViewById(R.id.submit);
        read = findViewById(R.id.read);
        name = findViewById(R.id.name);
        lat = findViewById(R.id.lat);
        lng = findViewById(R.id.lng);
        com = findViewById(R.id.com);
        id = findViewById(R.id.id);
        data = findViewById(R.id.data);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUserName = name.getText().toString();
                String getUserLat = lat.getText().toString();
                String getUserLng = lng.getText().toString();
                String getUserCom = com.getText().toString();

                HashMap result = new HashMap<>();
                result.put("name", getUserName); //키, 값
                result.put("lat", getUserLat);
                result.put("lng", getUserLng);
                result.put("com", getUserCom);

                writeUser(Integer.toString(i++), getUserName, getUserLat,getUserLng, getUserCom);

            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readUser(id.getText().toString());
            }
        });
    }

    private void writeUser(String userId, String name, String lat,String lng, String com) {
        User user = new User(name, lat,lng, com);

        //데이터 저장
        mDatabase.child("users").child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() { //데이터베이스에 넘어간 이후 처리
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"저장을 완료했습니다", Toast.LENGTH_LONG).show();
                        Log.w("저장완료","저장");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"저장에 실패했습니다" , Toast.LENGTH_LONG).show();
                    }
                });
    }
    private void readUser(String userId) {
        //데이터 읽기
        mDatabase.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.w("읽기시작","저장");
                User user = snapshot.getValue(User.class);
                Log.w("데이터불러옴","저장");
                data.setText("이름: " + user.name + " lat: " + user.lat +" lng: " + user.lng + " 회사: " + user.com);

                Log.w("id 1의 데이터", ""+mDatabase.child("user").child(userId));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { //참조에 액세스 할 수 없을 때 호출
                Toast.makeText(getApplicationContext(),"데이터를 가져오는데 실패했습니다" , Toast.LENGTH_LONG).show();
            }
        });
    }
}