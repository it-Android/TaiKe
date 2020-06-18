package com.example.taike01.mine_f;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.taike01.R;
import com.example.taike01.activity.ReadActivity;
import com.example.taike01.entityClass.JavaDbExhibition;
import com.example.taike01.entityClass.JavaOperateDataAggregate;
import com.example.taike01.tool.lite.JavaOperateTable;
import com.google.gson.Gson;

public class OperationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_simple;  //简单操作
    private Button btn_basic;   //基本操作
    private Button btn_complex; //综合操作
    private String type_name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        setViewByID();
        set_Click();
    }

    public void setViewByID(){
        btn_simple=(Button)findViewById(R.id.operation_btn_simple);
        btn_basic=(Button)findViewById(R.id.operation_btn_basic);
        btn_complex=(Button)findViewById(R.id.operation_btn_complex);
    }

    public void set_Click(){
        btn_simple.setOnClickListener(this);
        btn_basic.setOnClickListener(this);
        btn_complex.setOnClickListener(this);
    }

    public void operation_practice(){
        String gsonData;
        String openType=getString(R.string.type_practice);
        Gson gson=new Gson();

        JavaOperateTable operateTable=new JavaOperateTable(this);
        JavaOperateDataAggregate javaOperateDataAggregate=operateTable.queryType(type_name);
        JavaDbExhibition dbExhibition=new JavaDbExhibition();
        dbExhibition.setOperateDataAggregates(javaOperateDataAggregate);
        gsonData=gson.toJson(dbExhibition);

        Intent intent=new Intent(OperationActivity.this, ReadActivity.class);
        intent.putExtra("gson",gsonData);
        intent.putExtra("openType",openType);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.operation_btn_simple:
                type_name=btn_simple.getText().toString().trim();
                //传递数据type_name
                operation_practice();
                break;
            case R.id.operation_btn_basic:
                type_name=btn_basic.getText().toString().trim();
                //传递数据type_name
                operation_practice();
                break;
            case R.id.operation_btn_complex:
                type_name=btn_complex.getText().toString().trim();
                //传递数据type_name
                operation_practice();
                break;
        }
    }
}
