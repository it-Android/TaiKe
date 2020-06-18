package com.example.taike01.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taike01.R;
import com.example.taike01.activity.ReadActivity;
import com.example.taike01.entityClass.JavaKuEntity;
import com.example.taike01.tool.SpTool;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/4 15:51
 **/
public class ReadViewPagerFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, CheckBox.OnCheckedChangeListener{
    private JavaKuEntity kuEntity;
    private TextView tv_title,tv_answer,tv_explain;
    private RadioButton rbA,rbB,rbC,rbD;
    private RadioGroup radioGroup;
    private int viewpagerID;
    private LinearLayout ll;
    private CheckBox checkBox;
    private Boolean isTest=false;
    private Boolean isChoice=false;//是否已经选择过

    public ReadViewPagerFragment(JavaKuEntity kuEntity, int viewpagerID, Boolean isTest) {
        this.kuEntity = kuEntity;
        this.viewpagerID = viewpagerID;
        this.isTest = isTest;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.viewpager_read_layout,container,false);
        tv_title=(TextView)view.findViewById(R.id.vp_read_title);
        tv_answer=(TextView)view.findViewById(R.id.vp_read_answer);
        tv_explain=(TextView)view.findViewById(R.id.vp_read_explain);
        ll=(LinearLayout)view.findViewById(R.id.vp_read_ll);
        radioGroup=(RadioGroup)view.findViewById(R.id.lv_sb_rg);
        rbA=(RadioButton)view.findViewById(R.id.vp_read_rbA);
        rbB=(RadioButton)view.findViewById(R.id.vp_read_rbB);
        rbC=(RadioButton)view.findViewById(R.id.vp_read_rbC);
        rbD=(RadioButton)view.findViewById(R.id.vp_read_rbD);
        checkBox=(CheckBox)view.findViewById(R.id.vp_read_cb);
        checkBox.setOnCheckedChangeListener(this);
        if(isTest){
            checkBox.setChecked(false);
            checkBox.setVisibility(View.GONE);
        }else{
            checkBox.setVisibility(View.VISIBLE);

        }
        tv_title.setText((viewpagerID+1)+"、"+kuEntity.getTitle());
        tv_answer.setText("答案："+kuEntity.getAnswer());
        tv_explain.setText(kuEntity.getExplain());
        rbA.setText(kuEntity.getOptionA());
        rbB.setText(kuEntity.getOptionB());
        rbC.setText(kuEntity.getOptionC());
        rbD.setText(kuEntity.getOptionD());
        radioGroup.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        String answer=null;
        switch (checkedId){
            case R.id.vp_read_rbA:
                answer="A";
                break;
            case R.id.vp_read_rbB:
                answer="B";
                break;
            case R.id.vp_read_rbC:
                answer="C";
                break;
            case R.id.vp_read_rbD:
                answer="D";
                break;
        }
        if(!isTest){//当前是否为 练习模式
            hide(false);//将radiobutton设为不可用
            if(isTrue(answer)){//判断答案是否正确
                checkBox.setChecked(true);//展示答案
                if(!isChoice){//判断是否第一次点击
                    ((ReadActivity)getActivity()).switchItem();
                }
            }else{
                checkBox.setChecked(true);
            }
        }
        isChoice=true;
        ((ReadActivity)getActivity()).upData(viewpagerID,answer);
    }

    private void hide(Boolean isCheck){
        rbA.setEnabled(isCheck);
        rbB.setEnabled(isCheck);
        rbC.setEnabled(isCheck);
        rbD.setEnabled(isCheck);
    }



    private Boolean isTrue(String answer){
        Boolean isTrue=answer.equals(kuEntity.getAnswer());
        Drawable leftDrawable =null;
        if(!isTrue){
            leftDrawable= getActivity().getResources().getDrawable(R.drawable.radio_no);
            leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
            switch (answer){
                case "A":
                    rbA.setTextColor(android.graphics.Color.RED);
                    rbA.setCompoundDrawables(leftDrawable,null,null,null);
                    break;
                case "B":
                    rbB.setTextColor(android.graphics.Color.RED);
                    rbB.setCompoundDrawables(leftDrawable,null,null,null);
                    break;
                case "C":
                    rbC.setTextColor(android.graphics.Color.RED);
                    rbC.setCompoundDrawables(leftDrawable,null,null,null);
                    break;
                case "D":
                    rbD.setTextColor(android.graphics.Color.RED);
                    rbD.setCompoundDrawables(leftDrawable,null,null,null);
                    break;
            }
        }

        Drawable leftDrawableYe= getActivity().getResources().getDrawable(R.drawable.radio_ye);
        leftDrawableYe.setBounds(0, 0, leftDrawableYe.getMinimumWidth(), leftDrawableYe.getMinimumHeight());
        switch (kuEntity.getAnswer()){
            case "A":
                rbA.setTextColor(android.graphics.Color.BLUE);
                rbA.setCompoundDrawables(leftDrawableYe,null,null,null);
                break;
            case "B":
                rbB.setTextColor(android.graphics.Color.BLUE);
                rbB.setCompoundDrawables(leftDrawableYe,null,null,null);
                break;
            case "C":
                rbC.setTextColor(android.graphics.Color.BLUE);
                rbC.setCompoundDrawables(leftDrawableYe,null,null,null);
                break;
            case "D":
                rbD.setTextColor(android.graphics.Color.BLUE);
                rbD.setCompoundDrawables(leftDrawableYe,null,null,null);
                break;
        }



        return isTrue;
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.vp_read_cb:
                if(isChecked){
                    ll.setVisibility(View.VISIBLE);
                }else{
                    ll.setVisibility(View.GONE);
                }
                break;
        }
    }



}
