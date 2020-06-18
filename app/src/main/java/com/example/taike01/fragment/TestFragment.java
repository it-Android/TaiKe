package com.example.taike01.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taike01.R;
import com.example.taike01.activity.ReadActivity;
import com.example.taike01.entityClass.JavaDbExhibition;
import com.example.taike01.entityClass.JavaKuDataAggregate;
import com.example.taike01.entityClass.JavaOperateDataAggregate;
import com.example.taike01.tool.FileTool;
import com.example.taike01.tool.lite.JavaKuTable;
import com.example.taike01.tool.lite.JavaOperateTable;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/3 22:42
 **/
public class TestFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_test_layout,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ImageButton test_btn=(ImageButton)getActivity().findViewById(R.id.test_btn);
        test_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_btn:
                test_layout();
                break;
        }
    }

    public void test_layout(){
        String gsonData;//保存要传递的数据
        String openType=getString(R.string.type_test);//模拟考试
        Gson gson=new Gson();//json转换工具
        JavaKuTable kuTable=new JavaKuTable(getActivity());//选择题表工具
        JavaOperateTable operateTable=new JavaOperateTable(getActivity());//大题库表工具
        JavaKuDataAggregate javaKuDataAggregate = kuTable.queryAll();//获取Java体系结构 类型的选择题
        JavaOperateDataAggregate javaOperateDataAggregate = operateTable.queryAll();//获取 基本操作 类型的大题
        JavaDbExhibition dbExhibition=new JavaDbExhibition();//初始化题库数据

        //添加选择题库
        for(int ku:getRandomNumber(30,javaKuDataAggregate.getEntityList().size())){
            dbExhibition.getKuDataAggregates().getEntityList().add(javaKuDataAggregate.getEntityList().get(ku));
        }

        //添加大题
        for (int operate:getRandomNumber(3,javaOperateDataAggregate.getEntityList().size())){
            dbExhibition.getOperateDataAggregates().getEntityList().add(javaOperateDataAggregate.getEntityList().get(operate));
        }

        gsonData=gson.toJson(dbExhibition);//将数据转换为json
        Intent intent=new Intent(getActivity(), ReadActivity.class);
        intent.putExtra("gson",gsonData);//传递数据
        intent.putExtra("openType",openType);
        startActivity(intent);//跳转
    }



    //筛选题库
    private static List<Integer> getRandomNumber(int num,int number){
        List<Integer> list=new ArrayList<>();
        for(int i=0;i<num;i++){
            while (true){
                int id= (int) (Math.random()*number);
                Boolean isOk=true;
                for(int j:list){
                    if(id==j){
                        isOk=false;
                        break;
                    }
                }
                if(isOk) {
                    list.add(id);
                    break;
                }
            }
        }
        return list;
    }




}
