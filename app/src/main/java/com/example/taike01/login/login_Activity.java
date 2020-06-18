package com.example.taike01.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.taike01.R;
import com.example.taike01.activity.MainActivity;
import com.example.taike01.tool.SpTool;

import java.util.Timer;
import java.util.TimerTask;

public class login_Activity extends AppCompatActivity implements View.OnClickListener {
    private Button Login_btn;
    private Button Enrol_btn;
    private Button cs;
    private String UserNameData;
    private String PassWordData;
    private EditText UserName;
    private EditText PassWord;
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        i++;
        if(i==1){
            /*SpTool.append(login_Activity.this,"name","昵称");
            SpTool.append(login_Activity.this,"style","路曼曼其修远兮，吾将上下而求索。");*/
        }
        setContentView(R.layout.login_layout);
        UserName=(EditText)findViewById(R.id.username1);
        PassWord=(EditText)findViewById(R.id.password);
        Login_btn=(Button)findViewById(R.id.Login_btn);
        Enrol_btn=(Button)findViewById(R.id.Enrol1_btn);
        cs=(Button)findViewById(R.id.cs);
        Login_btn.setOnClickListener(this);
        cs.setOnClickListener(this);
        Enrol_btn.setOnClickListener(this);
        UserNameData=SpTool.readString(this,"username");
        PassWordData=SpTool.readString(this,"password");
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserNameData=SpTool.readString(this,"username");
        PassWordData=SpTool.readString(this,"password");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Enrol1_btn:
                Enrol();
                break;
            case R.id.Login_btn:
                Login();
                break;
            case R.id.cs:
                Intent intent = new Intent(login_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();//关闭当前界面
                break;

        }
    }


    public void Login(){
        if(!UserName.getText().toString().equals("")&&!PassWord.getText().toString().equals("")) {
            if (UserName.getText().toString().equals(UserNameData) && PassWord.getText().toString().equals(PassWordData)) {
                Toast.makeText(this, "成功登陆,正在跳转", Toast.LENGTH_SHORT).show();
                TimerTask task = new TimerTask() {
                    public void run() {
                        //execute the task
                        Intent intent = new Intent(login_Activity.this, MainActivity.class);
                        startActivity(intent);
                        finish();//关闭当前界面
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 500);
            } else Toast.makeText(this, "请输入正确的账号或密码", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(this, "用户名或密码不准为空", Toast.LENGTH_SHORT).show();}
    }
    public void Enrol(){
        Intent intent=new Intent(login_Activity.this, register.class);
        startActivity(intent);
    }
}