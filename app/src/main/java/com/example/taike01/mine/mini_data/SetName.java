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

public class SetName extends AppCompatActivity  implements View.OnClickListener {
    private Button SetName_Btn;
    private EditText SetName_Edit;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setname);
        SetName_Btn = (Button) findViewById(R.id.SetName_Btn);
        SetName_Edit = (EditText) findViewById(R.id.SetName_Edit);
        SetName_Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SetName_Btn:
                SetName();
                break;

        }
    }

    public void SetName() {
        if (!SetName_Edit.getText().equals("")) {
            if (SetName_Edit.getText().length() > 4) {
                Toast.makeText(SetName.this, "昵称长度不能超过4个文字", Toast.LENGTH_LONG).show();
                                                    }
            else                                    {
                data=SetName_Edit.getText().toString();
                SpTool.append(SetName.this,"name",data);
                Toast.makeText(SetName.this, "修改成功", Toast.LENGTH_LONG).show();
                SetName_Edit.setFocusableInTouchMode(false);
                Intent intent=new Intent(SetName.this, mine_data.class);
                intent.putExtra("intent","success1");
                startActivity(intent);
                finish();

                                                    }
        }
        else{}
    }
}