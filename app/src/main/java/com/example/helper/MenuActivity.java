package com.example.helper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button function1 = this.findViewById(R.id.function1);
        Button function2 = this.findViewById(R.id.function2);
        Button function3 = this.findViewById(R.id.function3);
        Button function4 = this.findViewById(R.id.function4);
        Button function5 = this.findViewById(R.id.function5);

        function1.setOnClickListener(this);
        function2.setOnClickListener(this);
        function3.setOnClickListener(this);
        function4.setOnClickListener(this);
        function5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.function1:
                //Toast.makeText(this, "Function1被按下", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,FinActivity.class));  //連接至FinActivity
                break;
            case R.id.function2:
                //Toast.makeText(this, "Function2被按下", Toast.LENGTH_SHORT).show();
                IntentIntegrator scanIntegrator;
                scanIntegrator = new IntentIntegrator(this);
                scanIntegrator.initiateScan();
                break;
            case R.id.function3:
                //Toast.makeText(this, "Function3被按下", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, BillingActivity.class));
                break;
            case R.id.function4:
                //Toast.makeText(this, "Function4被按下", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LotteryActivity.class));
                break;
            case R.id.function5:
                //Toast.makeText(this, "Function5被按下", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, TemperatureActivity.class));
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            Toast.makeText(this, "(" + scanFormat + ")" + scanContent, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_SHORT).show();
        }

    }

}