package com.example.taike01.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.taike01.activity.MainActivity;
import com.example.taike01.login.login_Activity;
import com.example.taike01.R;
import com.example.taike01.mine.mine_data;
import com.example.taike01.mine.Clock_in;
import com.example.taike01.mine.SetPassword_Activity;
import com.example.taike01.mine.mini_data.MineNote;
import com.example.taike01.tool.FileTool;
import com.example.taike01.tool.SpTool;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/3 22:42
 **/
public class MineFragment extends Fragment implements View.OnClickListener ,MainActivity.CallBack{
    private String DataName="昵称";
    private String DataSex="男/女";
    private LinearLayout minedata;
    private Button clock_in_layout;
    private Button setpassword;
    private Button mine_btn_note;

    private  TextView name1;
    private  TextView sex_text;

    private ImageView headIcon;

    private String MY_RMBCost ="MY_RMBCost";
    private String TodayTime ="TodayTime";

    private FileTool fileTool;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine_layout,container,false);
        return view;
    }

//wr
//最后修改时间：2019/10/10
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fileTool=new FileTool(getActivity());
        ((MainActivity)getActivity()).setCallBack(this);
        findViedById();
        Listener();
        DataName=SpTool.readString(getContext(),"name");
        DataSex=SpTool.readString(getContext(),"sex");

        headIcon.setImageResource(fileTool.getHeadImage());//添加头像


        if(DataName.equals("")){
            name1.setText("昵称");
        }
        else {name1.setText(DataName);}

        if(DataSex!="") {
            sex_text.setText(DataSex);
        }
        else {sex_text.setText("性别");}
        clock();


    }
    private void findViedById(){
        minedata=(LinearLayout)getView().findViewById(R.id.minedata);
        clock_in_layout=(Button) getView().findViewById(R.id.mine_btn_clock);
        setpassword=(Button) getView().findViewById(R.id.mine_btn_setpassword);
        name1=(TextView)getView().findViewById(R.id.name1);
        sex_text=(TextView)getView().findViewById(R.id.sex_text);
        mine_btn_note=(Button) getView().findViewById(R.id.mine_btn_note);
        headIcon=(ImageView)getView().findViewById(R.id.min_head1);
    }
    private void Listener(){
        minedata.setOnClickListener(this);
        setpassword.setOnClickListener(this);
        mine_btn_note.setOnClickListener(this);
    }
    private void clock(){
        Time time=new Time();
        time.setToNow();
        SharedPreferences my_rmb_data =getContext().getSharedPreferences(MY_RMBCost,0);
        final String str =  time.year + "年" + time.month+1 + "月" + time.monthDay + "日";
        final String nowtime=my_rmb_data.getString(TodayTime,"").toString();
      /*  if(nowtime.equals(str)==true)
        {
            Toast.makeText(getContext(),"今日已签到！",Toast.LENGTH_LONG).show();
        }
        else
        {

        }*/
        clock_in_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences my_rmb_data = getContext().getSharedPreferences(MY_RMBCost, 0);
                if(my_rmb_data.getString(TodayTime, "").toString().equals(str)==true)
                {
                    Intent intent=new Intent(getActivity(), Clock_in.class);
                    startActivity(intent);
                    Toast.makeText( getContext(), "今日已签到！", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    my_rmb_data.edit()
                            .putString(TodayTime, str)
                            .commit();
                    Intent intent=new Intent(getActivity(), Clock_in.class);
                    intent.putExtra("ClockIn","success");
                    startActivity(intent);
                    Toast.makeText( getContext(), "签到成功！", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        DataName=SpTool.readString(getContext(),"name");
        DataSex=SpTool.readString(getContext(),"sex");
        if(DataName.equals("")){
            name1.setText("昵称");
        }
        else {name1.setText(DataName);}

        if(DataSex!="") {
            sex_text.setText(DataSex);
        }
        else {sex_text.setText("性别");}
        clock();
    }

    public void minedata(){
        //修改个人资料
        Intent intent=new Intent(getActivity(), mine_data.class);
        startActivity(intent);
    }

    public void clock_in(){
        //签到
        Intent intent=new Intent(getActivity(), Clock_in.class);
        startActivity(intent);
        Toast.makeText(getContext(),"打卡签到系统暂未开放",Toast.LENGTH_LONG).show();

    }
    public void SetPassword(){
        //修改账号密码界面
        Intent intent=new Intent(getActivity(), SetPassword_Activity.class);
        startActivity(intent);

    }
    public void MineNote(){
        //账号密码清空

        Intent intent=new Intent(getActivity(), MineNote.class);
        startActivity(intent);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.minedata:
                minedata();
                break;
            case R.id.mine_btn_clock:
                clock_in();
                break;
            case R.id.mine_btn_setpassword:
                SetPassword();
                break;
            case R.id.mine_btn_note:
                MineNote();
                break;
        }
    }

    @Override
    public void onRestart() {
        headIcon.setImageResource(fileTool.getHeadImage());//添加头像
    }
}
