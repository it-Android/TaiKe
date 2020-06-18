package com.example.taike01.mine_f;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taike01.R;
import com.example.taike01.activity.ReadActivity;
import com.example.taike01.entityClass.JavaDbExhibition;
import com.example.taike01.entityClass.JavaKuDataAggregate;
import com.example.taike01.tool.lite.JavaKuTable;
import com.google.gson.Gson;

public class ChoiceActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox ck_opentype;
    private TextView tv_body1,tv_body2,tv_body3,tv_body4,tv_body5,tv_body6,tv_body7,tv_body8,tv_body9,tv_body10,
            tv_body11,tv_body12,tv_body13,tv_body14,tv_body15,tv_body16,tv_body17,tv_body18,tv_body19,tv_body20,
            tv_body21,tv_body22,tv_body23,tv_body24,tv_body25,tv_body26,tv_body27,tv_body28,tv_body29,tv_head;
    private String type_name;
    private
    JavaKuTable kuTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        kuTable=new JavaKuTable(this);
        getViewByID();
        set_Click();
        open_Type();
    }

    public void getViewByID(){
        ck_opentype=(CheckBox) findViewById(R.id.check_choice_type);
        tv_head=(TextView)findViewById(R.id.choice_type_head);
        tv_body1=(TextView)findViewById(R.id.choice_type_body1);
        tv_body2=(TextView)findViewById(R.id.choice_type_body2);
        tv_body3=(TextView)findViewById(R.id.choice_type_body3);
        tv_body4=(TextView)findViewById(R.id.choice_type_body4);
        tv_body5=(TextView)findViewById(R.id.choice_type_body5);
        tv_body6=(TextView)findViewById(R.id.choice_type_body6);
        tv_body7=(TextView)findViewById(R.id.choice_type_body7);
        tv_body8=(TextView)findViewById(R.id.choice_type_body8);
        tv_body9=(TextView)findViewById(R.id.choice_type_body9);
        tv_body10=(TextView)findViewById(R.id.choice_type_body10);
        tv_body11=(TextView)findViewById(R.id.choice_type_body11);
        tv_body12=(TextView)findViewById(R.id.choice_type_body12);
        tv_body13=(TextView)findViewById(R.id.choice_type_body13);
        tv_body14=(TextView)findViewById(R.id.choice_type_body14);
        tv_body15=(TextView)findViewById(R.id.choice_type_body15);
        tv_body16=(TextView)findViewById(R.id.choice_type_body16);
        tv_body17=(TextView)findViewById(R.id.choice_type_body17);
        tv_body18=(TextView)findViewById(R.id.choice_type_body18);
        tv_body19=(TextView)findViewById(R.id.choice_type_body19);
        tv_body20=(TextView)findViewById(R.id.choice_type_body20);
        tv_body21=(TextView)findViewById(R.id.choice_type_body21);
        tv_body22=(TextView)findViewById(R.id.choice_type_body22);
        tv_body23=(TextView)findViewById(R.id.choice_type_body23);
        tv_body24=(TextView)findViewById(R.id.choice_type_body24);
        tv_body25=(TextView)findViewById(R.id.choice_type_body25);
        tv_body26=(TextView)findViewById(R.id.choice_type_body26);
        tv_body27=(TextView)findViewById(R.id.choice_type_body27);
        tv_body28=(TextView)findViewById(R.id.choice_type_body28);
        tv_body29=(TextView)findViewById(R.id.choice_type_body29);
    }

    public void set_Click(){
        tv_head.setOnClickListener(this);
        tv_body1.setOnClickListener(this);
        tv_body2.setOnClickListener(this);
        tv_body3.setOnClickListener(this);
        tv_body4.setOnClickListener(this);
        tv_body5.setOnClickListener(this);
        tv_body6.setOnClickListener(this);
        tv_body7.setOnClickListener(this);
        tv_body8.setOnClickListener(this);
        tv_body9.setOnClickListener(this);
        tv_body10.setOnClickListener(this);
        tv_body11.setOnClickListener(this);
        tv_body12.setOnClickListener(this);
        tv_body13.setOnClickListener(this);
        tv_body14.setOnClickListener(this);
        tv_body15.setOnClickListener(this);
        tv_body16.setOnClickListener(this);
        tv_body17.setOnClickListener(this);
        tv_body18.setOnClickListener(this);
        tv_body19.setOnClickListener(this);
        tv_body20.setOnClickListener(this);
        tv_body21.setOnClickListener(this);
        tv_body22.setOnClickListener(this);
        tv_body23.setOnClickListener(this);
        tv_body24.setOnClickListener(this);
        tv_body25.setOnClickListener(this);
        tv_body26.setOnClickListener(this);
        tv_body27.setOnClickListener(this);
        tv_body28.setOnClickListener(this);
        tv_body29.setOnClickListener(this);
    }

    public void open_Type(){ //显示和隐藏我的题类
        ck_opentype.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischeck) {
                if (ischeck){
                    LinearLayout linearLayout=(LinearLayout)findViewById(R.id.choice_type_body);
                    linearLayout.setVisibility(View.VISIBLE);
                }else {
                    LinearLayout linearLayout=(LinearLayout)findViewById(R.id.choice_type_body);
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    public void choice_Practice_Body(){
        String gsonData;
        String openType=getString(R.string.type_practice);//普通模式
        Gson gson=new Gson();
        kuTable=new JavaKuTable(this);
        JavaKuDataAggregate javaKuDataAggregate=kuTable.queryType(type_name);
        JavaDbExhibition dbExhibition=new JavaDbExhibition();
        dbExhibition.setKuDataAggregates(javaKuDataAggregate);
        gsonData=gson.toJson(dbExhibition);

        Intent intent=new Intent(ChoiceActivity.this, ReadActivity.class);
        intent.putExtra("gson",gsonData);
        intent.putExtra("openType",openType);
        startActivity(intent);
    }

    public void choice_Practice_Head(){
        String openType=getString(R.string.type_practice);//普通模式
        Intent intent=new Intent(ChoiceActivity.this, ReadActivity.class);
        intent.putExtra("gson","全部");
        intent.putExtra("openType",openType);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.choice_type_head:
                type_name=tv_head.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Head();
                break;
            case R.id.choice_type_body1:
                type_name=tv_body1.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body2:
                type_name=tv_body2.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body3:
                type_name=tv_body3.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body4:
                type_name=tv_body4.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body5:
                type_name=tv_body5.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body6:
                type_name=tv_body6.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body7:
                type_name=tv_body7.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body8:
                type_name=tv_body8.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body9:
                type_name=tv_body9.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body10:
                type_name=tv_body10.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body11:
                type_name=tv_body11.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body12:
                type_name=tv_body12.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body13:
                type_name=tv_body13.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body14:
                type_name=tv_body14.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body15:
                type_name=tv_body15.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body16:
                type_name=tv_body16.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body17:
                type_name=tv_body17.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body18:
                type_name=tv_body18.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body19:
                type_name=tv_body19.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body20:
                type_name=tv_body20.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body21:
                type_name=tv_body21.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body22:
                type_name=tv_body22.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body23:
                type_name=tv_body23.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body24:
                type_name=tv_body24.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body25:
                type_name=tv_body25.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body26:
                type_name=tv_body26.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body27:
                type_name=tv_body27.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body28:
                type_name=tv_body28.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
            case R.id.choice_type_body29:
                type_name=tv_body29.getText().toString().trim();
                //传递数据type_name
                choice_Practice_Body();
                break;
        }
    }
}
