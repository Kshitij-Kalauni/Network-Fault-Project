package com.example.myapplication2;

import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity2 extends AppCompatActivity {
    EditText t1,t2,t3,t4,t5;
    Button btn,btnnext;
    FirebaseFirestore dbroot= FirebaseFirestore.getInstance();
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(EditText)findViewById(R.id.t1);
        t2=(EditText)findViewById(R.id.t2);
        t3=(EditText)findViewById(R.id.t3);
        t4=(EditText)findViewById(R.id.t4);
        t5=(EditText)findViewById(R.id.t5);
        btn=(Button)findViewById(R.id.btnadd);
        btnnext=(Button)findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Activity2.this,Activity3.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,String> items=new HashMap<>();
                items.put("IP address",t1.getText().toString().trim());
                items.put("website name",t2.getText().toString().trim());
                items.put("Sender-Receiver-Distance",t3.getText().toString().trim());
                items.put("Packet Size",t4.getText().toString().trim());
                items.put("Bandwidth",t5.getText().toString().trim());
                dbroot.collection("network-fault-project").add(items);
                addOnContextAvailableListener(new OnContextAvailableListener() {
                    @Override
                    public void onContextAvailable(@NonNull Context context) {
                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        t5.setText("");
                        Toast.makeText(getApplicationContext(),"Inserted successfully",Toast.LENGTH_LONG);
                    }
                });
            }

        });


    }
}
