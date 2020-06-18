package com.example.taike01.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taike01.R;
import com.example.taike01.entityClass.JavaKuEntity;
import com.example.taike01.entityClass.JavaOperateEntity;
import com.example.taike01.tool.FileTool;
import com.example.taike01.tool.SystemTool;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/4 15:51
 **/
public class ReadViewPagerOperateFragment extends Fragment implements CheckBox.OnCheckedChangeListener{
    private JavaOperateEntity operateEntity;
    private TextView tv_title,tv_answer,tv_material;
    private ImageView imageView;
    private CheckBox checkBox;
    private int viewpagerID;
    private FileTool fileTool;
    public ReadViewPagerOperateFragment(JavaOperateEntity operateEntity, int viewpagerID) {
        this.operateEntity = operateEntity;
        this.viewpagerID = viewpagerID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.viewpager_read_operate_layout,container,false);
        fileTool=new FileTool(getActivity());
        tv_title=(TextView)view.findViewById(R.id.read_tv_operate_title);
        tv_answer=(TextView)view.findViewById(R.id.read_tv_operate_answer);
        tv_material=(TextView)view.findViewById(R.id.read_tv_operate_material);
        imageView=(ImageView)view.findViewById(R.id.read_iv_operate_photo);
        tv_title.setText((viewpagerID+1)+"、"+operateEntity.getTitle());
        checkBox=(CheckBox)view.findViewById(R.id.read_cb_operate_cb);
        checkBox.setOnCheckedChangeListener(this);
        tv_answer.setText(operateEntity.getAnswer());
        tv_material.setText(operateEntity.getMaterial());
        init();
        return view;
    }

    private void init(){
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),fileTool.getResId(operateEntity.getPhoto()), null);
        if(bitmap==null){
            imageView.setVisibility(View.GONE);
            return;
        }else{
            imageView.setVisibility(View.VISIBLE);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        double number=SystemTool.getScreenWidth(getActivity())/bitmap.getWidth();
        layoutParams.height= (int) (bitmap.getHeight()*number);
        layoutParams.width= SystemTool.getScreenWidth(getActivity());
        imageView.setImageBitmap(bitmap);
        imageView.setLayoutParams(layoutParams);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.read_cb_operate_cb:
                if(isChecked){
                    tv_answer.setVisibility(View.VISIBLE);
                }else{
                    tv_answer.setVisibility(View.GONE);
                }
                break;
        }
    }
}
