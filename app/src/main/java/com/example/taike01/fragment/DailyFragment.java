package com.example.taike01.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taike01.R;
import com.example.taike01.entityClass.JavaKuDataAggregate;
import com.example.taike01.entityClass.JavaOperateDataAggregate;
import com.example.taike01.mine_f.ChoiceActivity;
import com.example.taike01.mine_f.CollectActivity;
import com.example.taike01.mine_f.MistakenActivity;
import com.example.taike01.mine_f.OperationActivity;
import com.example.taike01.tool.FileTool;
import com.example.taike01.tool.lite.JavaKuTable;
import com.example.taike01.tool.lite.JavaOperateTable;
import com.google.gson.Gson;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/3 22:42
 **/
public class DailyFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_daily_layout,container,false);
        JavaKuTable kuTable=new JavaKuTable(getActivity());//选择题表
        JavaOperateTable operateTable=new JavaOperateTable(getActivity());//库不表
        Gson gson=new Gson();
        if(!kuTable.isTableExits()){//判断表是否存在
            kuTable.creatTable(null);
            operateTable.creatTable(null);
            FileTool fileTool=new FileTool(getActivity());//获取文件操作类对象
            kuTable.addData(gson.fromJson(fileTool.getRawText(R.raw.java_ku), JavaKuDataAggregate.class));//添加数据
            operateTable.addData(gson.fromJson(fileTool.getRawText(R.raw.java_operate), JavaOperateDataAggregate.class));
        }
        return view;
    }


   @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button btn_operation=(Button)getView().findViewById(R.id.daily_btn_operation);
        final Button btn_choice=(Button)getView().findViewById(R.id.daily_btn_choice);
        final Button btn_collect=(Button)getView().findViewById(R.id.daily_btn_collect);
        final Button btn_mistaken=(Button)getView().findViewById(R.id.daily_btn_mistaken);
        btn_operation.setOnClickListener(this);
        btn_choice.setOnClickListener(this);
        btn_collect.setOnClickListener(this);
        btn_mistaken.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.daily_btn_operation:
                Intent intent1=new Intent(getActivity(),OperationActivity.class);
                startActivity(intent1);
                break;
            case R.id.daily_btn_choice:
                Intent intent2=new Intent(getActivity(), ChoiceActivity.class);
                startActivity(intent2);
                break;
            case R.id.daily_btn_collect:
                Intent intent3=new Intent(getActivity(),CollectActivity.class);
                startActivity(intent3);
                break;
            case R.id.daily_btn_mistaken:
                Intent intent4=new Intent(getActivity(), MistakenActivity.class);
                startActivity(intent4);
                break;
        }
    }


}
