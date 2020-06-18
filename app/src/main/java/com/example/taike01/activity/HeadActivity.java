package com.example.taike01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.taike01.R;
import com.example.taike01.adapter.GrideViewHeadAdapter;
import com.example.taike01.tool.SpTool;

import java.util.ArrayList;
import java.util.List;

public class HeadActivity extends AppCompatActivity implements GridView.OnItemClickListener{
    private GridView gridView;
    private GrideViewHeadAdapter adapter;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        list=new ArrayList<>();
        for(int i=1;i<15;i++){
            list.add("tx"+i);
        }
        gridView=(GridView)findViewById(R.id.head_gv_01);
        adapter=new GrideViewHeadAdapter(this, list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String imageName=list.get(position);
        SpTool.append(this,SpTool.SP_HEAD_ID,imageName);
    }
}
