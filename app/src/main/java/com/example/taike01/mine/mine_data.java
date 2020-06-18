package com.example.taike01.mine;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taike01.R;
import com.example.taike01.activity.HeadActivity;
import com.example.taike01.activity.MainActivity;
import com.example.taike01.login.login_Activity;
import com.example.taike01.mine.mini_data.MineStyle;
import com.example.taike01.mine.mini_data.SetName;
import com.example.taike01.tool.FileTool;
import com.example.taike01.tool.SpTool;
import com.example.taike01.login.register;


public class mine_data extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout sethead;
    private LinearLayout setname;
    private LinearLayout setqm;
    private Button Logout;
    //本界面头像
    private ImageView headIcon;
    private TextView name3;
    private TextView Style;
    private String NameData="";
    private String StyleData;
    private static String path="/head/";
    private Spinner sex;
    private String intent_data;
    private FileTool fileTool;
    //wr
    //10/10
    private void findViewById(){
        sethead=(LinearLayout)findViewById(R.id.sethead);
        setname=(LinearLayout)findViewById(R.id.setname);
        setqm=(LinearLayout)findViewById(R.id.setqm);
        Logout=(Button)findViewById(R.id.Logout);
        sex=(Spinner)findViewById(R.id.sex);
        name3=(TextView)findViewById(R.id.name3);
        Style=(TextView)findViewById(R.id.qm);
        headIcon=(ImageView)findViewById(R.id.head3);
    }
    private void Listener(){
        sethead.setOnClickListener(this);
        setname.setOnClickListener(this);
        setqm.setOnClickListener(this);
        Logout.setOnClickListener(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_data);
        fileTool=new FileTool(this);
        findViewById();
        String data=SpTool.readString(this,"sex");

        headIcon.setImageResource(fileTool.getHeadImage());//添加头像

        if(data!=null) {
            if (data.equals("男")) {
                sex.setSelection(0, true);
            }
            if (data.equals("女")) {
                sex.setSelection(1, true);
            }
        }

        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String data=sex.getSelectedItem().toString();
                SpTool.append(mine_data.this,"sex",data);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Listener();


       /* Intent intent=this.getIntent();
        intent_data=intent.getStringExtra("intent");*/

        //获取数据
        NameData=SpTool.readString(mine_data.this,"name");
        name3.setText(NameData);
        StyleData=SpTool.readString(mine_data.this,"style");
        Style.setText(StyleData);

    }

    //重新回到页面时
    @Override
    protected void onResume() {
        super.onResume();
        NameData=SpTool.readString(mine_data.this,"name");
        name3.setText(NameData);
        StyleData=SpTool.readString(mine_data.this,"style");
        Style.setText(StyleData);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sethead:
                sethead();
                break;
            case R.id.setname:
                setname();
                break;
            case R.id.setqm:
                setqm();
                break;
            case R.id.Logout:
                Intent intent=new Intent(mine_data.this,login_Activity.class);
                startActivity(intent);
                MainActivity.activity.finish();
                finish();
                break;
        }
    }

    public void sethead(){
       //不跳转，直接修改头像
        startActivity(new Intent(this, HeadActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        headIcon.setImageResource(fileTool.getHeadImage());//添加头像
    }

    public void setname(){
    Intent intent=new Intent(mine_data.this, SetName.class);
    startActivity(intent);
    finish();
    }
    public void setqm(){
        Intent intent=new Intent(mine_data.this, MineStyle.class);
        startActivity(intent);
        finish();
    }
}
