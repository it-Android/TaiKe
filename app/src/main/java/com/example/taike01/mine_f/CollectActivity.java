package com.example.taike01.mine_f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.taike01.R;
import com.example.taike01.activity.ReadActivity;
import com.example.taike01.entityClass.JavaDbExhibition;
import com.example.taike01.entityClass.JavaKuDataAggregate;
import com.example.taike01.entityClass.JavaOperateDataAggregate;
import com.example.taike01.tool.lite.JavaKuTable;
import com.example.taike01.tool.lite.JavaOperateTable;
import com.google.gson.Gson;

public class CollectActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_choice,btn_operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        btn_choice=(Button)findViewById(R.id.collect_btn_choice);
        btn_operation=(Button)findViewById(R.id.collect_btn_operation);
        btn_choice.setOnClickListener(this);
        btn_operation.setOnClickListener(this);
    }

    public void collect_Choice_Practice(){
        String gsonData;
        String openType=getString(R.string.type_practice);
        Gson gson=new Gson();

        JavaKuTable kuTable=new JavaKuTable(this);
        JavaKuDataAggregate javaKuDataAggregate=kuTable.queryCollect("1");
        JavaDbExhibition dbExhibition=new JavaDbExhibition();
        dbExhibition.setKuDataAggregates(javaKuDataAggregate);
        if (dbExhibition.getNumber()==0){
            Toast.makeText(CollectActivity.this,"您还没有收藏选择题！",Toast.LENGTH_LONG).show();
        }else {
            gsonData = gson.toJson(dbExhibition);

            Intent intent = new Intent(CollectActivity.this, ReadActivity.class);
            intent.putExtra("gson", gsonData);
            intent.putExtra("openType", openType);
            startActivity(intent);
        }
    }

    public void collect_Operation_Practice(){
        String gsonData;
        String openType=getString(R.string.type_practice);
        Gson gson=new Gson();

        JavaOperateTable operateTable=new JavaOperateTable(this);
        JavaOperateDataAggregate javaOperateDataAggregate=operateTable.queryCollect("1");
        JavaDbExhibition dbExhibition=new JavaDbExhibition();
        dbExhibition.setOperateDataAggregates(javaOperateDataAggregate);
        if (dbExhibition.getNumber()==0){
            Toast.makeText(CollectActivity.this,"您还没有收藏操作题！",Toast.LENGTH_LONG).show();
        }else {
            gsonData = gson.toJson(dbExhibition);

            Intent intent = new Intent(CollectActivity.this, ReadActivity.class);
            intent.putExtra("gson", gsonData);
            intent.putExtra("openType", openType);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.collect_btn_choice:
                collect_Choice_Practice();
                break;
            case R.id.collect_btn_operation:
                collect_Operation_Practice();
                break;
        }
    }
}
