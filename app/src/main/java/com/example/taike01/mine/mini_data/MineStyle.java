package com.example.taike01.mine.mini_data;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taike01.R;
import com.example.taike01.mine.mine_data;
import com.example.taike01.tool.SpTool;

public class MineStyle extends AppCompatActivity  implements View.OnClickListener {
    private Button SetStyle_Btn;
    private EditText SetStyle_Edit;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minestyle);
        SetStyle_Btn = (Button) findViewById(R.id.SetStyle_Btn);
        SetStyle_Edit = (EditText) findViewById(R.id.SetStyle_Edit);
        SetStyle_Btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SetStyle_Btn:
                SetName();
                break;

        }
    }

    public void SetName() {
        if (!SetStyle_Edit.getText().equals("")) {
            if (SetStyle_Edit.getText().length() > 10) {
                Toast.makeText(MineStyle.this, "个性签名长度不能超过10个文字", Toast.LENGTH_LONG).show();
            }
            else                                    {
                Toast.makeText(MineStyle.this, "修改成功", Toast.LENGTH_LONG).show();
                data=SetStyle_Edit.getText().toString();
                SpTool.append(MineStyle.this,"style",data);
                Intent intent=new Intent(MineStyle.this, mine_data.class);
                intent.putExtra("intent","success2");
                startActivity(intent);
                finish();

            }
        }
        else{}
    }
}
