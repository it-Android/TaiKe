package com.example.taike01.login;

import androidx.appcompat.app.AppCompatActivity;
import com.example.taike01.R;
import com.example.taike01.tool.SpTool;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity implements View.OnClickListener {
    private String NameData;
    private String PassWord1Data;
    private String PassWord2Data;
    private EditText Edit_Name;
    private EditText Edit_PassWord1;
    private EditText Edit_PassWord2;
    private Button Enrol;
    private Button Cancle;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Edit_Name=(EditText)findViewById(R.id.username2);
        Edit_PassWord1=(EditText)findViewById(R.id.PassWord1);
        Edit_PassWord2=(EditText)findViewById(R.id.PassWord2);
        Enrol=(Button)findViewById(R.id.Enrol2_btn);
        Cancle=(Button)findViewById(R.id.remove_btn);
        Enrol.setOnClickListener(this);
        Cancle.setOnClickListener(this);
        textView=(TextView)findViewById(R.id.register_ad);
        textView.setSelected(true);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Enrol2_btn:
                Enrol();
                break;
            case R.id.remove_btn:
                finish();
                break;
        }
    }
    private void Enrol(){
        NameData=Edit_Name.getText().toString().trim();
        PassWord1Data=Edit_PassWord1.getText().toString().trim();
        PassWord2Data=Edit_PassWord2.getText().toString().trim();
        if(!NameData.equals("")&&!PassWord1Data.equals("")) {

            if(NameData.length() <2){
                Toast.makeText(this, "账号长度小于2", Toast.LENGTH_SHORT).show();
                return;
            }
            if(NameData.length() >8){
                Toast.makeText(this, "账号长度大于8", Toast.LENGTH_SHORT).show();
                return;
            }
            if(PassWord1Data.length()!=6){
                Toast.makeText(this, "密码长度必须6位", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!PassWord1Data.equals(PassWord2Data)){
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            SpTool.append(register.this, "username", NameData);
            SpTool.append(register.this, "password", PassWord1Data);
            //SpTool.append(register.this,"password2",PassWord2Data);
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

        }else Toast.makeText(this, "账号或密码不准为空", Toast.LENGTH_SHORT).show();
    }



    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }



}
