package com.example.taike01.mine;

import androidx.appcompat.app.AppCompatActivity;
import com.example.taike01.R;
import com.example.taike01.login.login_Activity;
import com.example.taike01.mine.mini_data.SetName;
import com.example.taike01.tool.FileTool;
import com.example.taike01.tool.SpTool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SetPassword_Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView name2;
    private String data="昵称";
    private EditText OldPassWord;
    private EditText NewPassWord1;
    private EditText NewPassWord2;
    private String oldPassword;
    private String newPassWord;
    private Button FixSet;
    private Button Quit;

    private ImageView headIcon;
    private FileTool fileTool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        fileTool=new FileTool(this);
        name2=(TextView)findViewById(R.id.name2);
        //获取数据
        data= SpTool.readString(getBaseContext(),"name");
        name2.setText(data);
        OldPassWord=(EditText)findViewById(R.id.OldPassWord);
        NewPassWord1=(EditText)findViewById(R.id.NewPassWord1);
        NewPassWord2=(EditText)findViewById(R.id.NewPassWord2);
        headIcon=(ImageView)findViewById(R.id.head2);


        headIcon.setImageResource(fileTool.getHeadImage());//添加头像


        FixSet=(Button)findViewById(R.id.FixSet);
        Quit=(Button)findViewById(R.id.Quit);
        FixSet.setOnClickListener(this);
        Quit.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        data= SpTool.readString(getBaseContext(),"name");
        name2.setText(data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.FixSet:
                SetPassWord();
                break;
            case R.id.Quit:
                finish();
                break;
        }
    }
    private void SetPassWord(){
        oldPassword=SpTool.readString(this,"password");
        if(oldPassword.equals(OldPassWord.getText().toString().trim())){
            if(NewPassWord1.getText().toString().trim().equals(NewPassWord2.getText().toString().trim())){
                newPassWord=NewPassWord1.getText().toString();
                if(newPassWord.length()==6){
                    SpTool.append(this,"password",newPassWord);
                    Toast.makeText(this,"密码修改成功，请重新登陆",Toast.LENGTH_SHORT).show();
                    TimerTask task = new TimerTask(){
                        public void run(){
                            //execute the task
                            Intent intent=new Intent(SetPassword_Activity.this, login_Activity.class);
                            startActivity(intent);
                        }
                    };
                    Timer timer = new Timer();
                    timer.schedule(task, 500);
                }
            }else {Toast.makeText(this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();}
        }else {
            Toast.makeText(this,"原密码错误",Toast.LENGTH_SHORT).show();
        }
    }

}

