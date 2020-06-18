package com.example.taike01.mine;

import androidx.appcompat.app.AppCompatActivity;
import com.example.taike01.R;
import com.example.taike01.tool.SpTool;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Clock_in extends AppCompatActivity {
    private TextView day;
    private String intent_data;
    private static String Day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_in);
        day = (TextView) findViewById(R.id.day);
        Intent intent = this.getIntent();
        intent_data = intent.getStringExtra("ClockIn");
        if (intent_data!=null) {
            if (intent_data.equals("success")) {
                String data=day.getText().toString();
                int i=Integer.parseInt(data);
                i++;
                Day=String.valueOf(i);
                day.setText(Day);
                SpTool.append(this,"day",day.getText().toString());
            }
        }else{
            String data=SpTool.readString(this,"day");
            int i=Integer.parseInt(data);
            Day=String.valueOf(i);
            day.setText(Day);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
