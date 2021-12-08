package com.example.myapplication2;

import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Activity3 extends AppCompatActivity {
    TextView tv;
    String net;
    String avail;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    int j=1;
    String IP[] = {"103.123.226.10",
            "110.172.55.126",
            "182.19.95.34",
            "202.62.224.2",
            "202.138.120.86",
            "103.23.150.89",
            "117.55.243.14",
            "182.19.95.98",
            "119.235.52.2",
            "203.8.201.11",
            "182.237.16.7",
            "202.134.52.105",
            "202.134.52.103",
            "103.15.60.23",
            "202.129.196.24,2",
            "103.205.15.1",
            "45.79.120.233",
            "103.103.212.222",
            "182.255.58.58",
            "164.100.138.248",
            "106.51.72.222",
            "136.232.194.10",
            "183.87.69.233",
            "183.87.70.210",
            "43.249.219.198",
            "3.6.210.233",
            "2a0f:4a80:0:5::6734",
            "14.102.19.50",
            "15.207.59.162",
            "194.195.119.4",
            "152.67.163.136",
            "140.238.225.30",
            "3.108.235.244",
            "15.207.52.93",
            "34.93.138.246",
            "159.89.173.198",
            "3.108.201.238",
            "152.70.64.229",
            "159.89.170.101",
            "65.1.139.6",
            "34.93.140.196",
            "143.244.134.191",
            "164.52.192.24",
            "61.246.33.46",
            "140.238.244.123",
            "13.234.19.52",
            "20.193.247.131",
            "142.93.223.7",
            "13.232.100.164",
            "192.46.208.224",
            "65.1.35.223",
            "140.238.229.220",
            "172.105.40.9",
            "140.238.247.244",
            "115.124.98.90",
            "68.183.247.170",
            "123.201.128.143",
            "103.100.220.34",
            "194.195.113.183",
            "52.66.143.134",
            "13.234.117.17",
            "134.209.156.123",
            "139.59.24.29",
            "3.7.102.159",
            "13.234.225.147",
            "13.71.89.186",
            "152.67.6.21",
            "159.65.148.233",
            "139.59.51.162",
            "172.105.49.21",
            "65.1.198.98",
            "13.234.32.67",
            "13.127.180.231",
            "45.248.57.146",
            "103.10.210.17",
            "45.116.1.54",
            "103.101.116.129",
            "103.159.200.3",
            "45.64.10.161",
            "136.232.125.70",
            "45.65.49.89",
            "36.255.184.22",
            "103.217.249.129",
            "45.64.8.30",
            "103.248.93.5",
            "103.93.136.8",
            "182.72.151.186",
            "117.198.218.134",
            "103.101.100.27",
            "103.101.100.28",
            "103.79.74.1",
            "117.206.83.218",
            "202.53.95.14",
            "103.194.120.3",
            "223.31.121.171",
            "111.93.163.56",
            "202.164.44.246"};
    int ctr = IP.length;
    String DNS[] = {
            "www.google.com",
            "www.facebook.com",
            "www.youtube.com",
            "www.instagram.com",
            "support.google.com",
            "www.quora.com",
            "www.myspace.com",
            "www.googleplus.com",
            "web.whatsapp.com",
            "www.turboVPN.com",
            "www.amazon.com",
            "www.paytm.com",
            "www.Audible.com",
            "www.Quora.com",
            "www.microsoft.com",
            "www.UPES.ac.in",
            "www.upGrad.com",
            "www.flipkart.com",
            "www.coursera.com"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        tv = (TextView)findViewById(R.id.textdata);
        fetchdata();
        tv.setText("");


    }
    public String checkConnection()
    {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if(null!=activeNetwork){
            if(activeNetwork.getType()==ConnectivityManager.TYPE_WIFI){
                net = "Connected to Wi-Fi";
            }

            else if(activeNetwork.getType()==ConnectivityManager.TYPE_MOBILE){
                net = "Connected to Mobile Data";
            }

        }

        else {
            net = "No Internet Connection!";
        }
        return net;
    }
    public String DNSCheck(String s)
    {
        int flag2=0;
        for(int i=0;i<DNS.length;i++)
        {
            if(s.equals(DNS[i]))
                flag2=1;
        }
        if(flag2==1)
            return "Valid DNS";
        else
            return "Invalid DNS";
    }
    public String Valid(String av){
        if(urlValid(av)){
            avail = "URL Format Correct";
        }
        else avail = "URL Format Incorrect";

        return avail;
    }

    public static boolean urlValid(String lr){
        try {
            new URL(lr).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public void fetchdata() {
        db.collection("network-fault-project")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            tv.append("Faults in Network "+j+"\n");
                            int flag = 0;
                            int k = 0;
                            String ps, srd, ip, bw, wn;
                            ip = document.getString("IP address");
                            wn = document.getString("website name");
                            srd = document.getString("Sender-Receiver-Distance");
                            ps = document.getString("Packet Size");
                            bw = document.getString("Bandwidth");
                            int ps2 = Integer.parseInt(ps);
                            int srd2 = Integer.parseInt(srd);
                            int bw2 = Integer.parseInt(bw);
                            String checknet=checkConnection();
                            String collectval=DNSCheck(wn);
                            String st=Valid(wn);
                            tv.append(st+"\n");
                            tv.append(collectval+"\n");
                            for (k = 0; k <= ctr - 1; k++) {
                                if (ip.equals(IP[k])) {
                                    flag = 1;
                                }

                            }
                            if (flag == 1)
                                tv.append("IP correct" + "\n");
                            else
                                tv.append("Wrong IP" + "\n");
                            float prdelay = (srd2 * 128) / ps2;
                            float rtt = 2 * prdelay;
                            if (rtt < 100)
                                tv.append("Network in good health \n");
                            else if (rtt >= 100 && rtt <= 200)
                                tv.append("Network is in medium health \n");
                            else if (rtt >= 200 && rtt <= 375)
                                tv.append("Network is in poor health \n");
                            else if (rtt > 375)
                                tv.append("Network will not work \n");
                            tv.append(checknet+"\n");
                            tv.append("\n");
                            j++;

                        }
                    }
                });
    }
}
