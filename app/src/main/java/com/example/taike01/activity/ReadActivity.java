package com.example.taike01.activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taike01.R;
import com.example.taike01.adapter.ReadViewPagesAdapter;
import com.example.taike01.alertDialog.ChoiceDialog;
import com.example.taike01.alertDialog.WarnDialog;
import com.example.taike01.entityClass.JavaDbExhibition;
import com.example.taike01.entityClass.JavaKuDataAggregate;
import com.example.taike01.entityClass.JavaKuEntity;
import com.example.taike01.entityClass.JavaOperateDataAggregate;
import com.example.taike01.mine_f.ChoiceActivity;
import com.example.taike01.tool.FileTool;
import com.example.taike01.tool.SystemTool;
import com.example.taike01.tool.ThreadTool;
import com.example.taike01.tool.lite.JavaKuTable;
import com.example.taike01.tool.lite.JavaOperateTable;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ReadActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener{
    private ViewPager viewPager;//左右切换
    private ReadViewPagesAdapter adapter;//适配器
    private Toolbar toolbar;//
    private JavaKuTable javaKuTable;//Java库操作对象
    private JavaOperateTable operateTable;//大题库表工具
    private TextView tv_time;//显示时间
    private ThreadTool threadTool;//线程操作工具
    private CheckBox checkBox;//收藏按钮
    private TextView tv_btn, tv_correct,tv_mistaken,tv_progress;//错题显示与对题显示
    private LinearLayout btnBox;
    private Map<Integer,String> mapAnswer;//保存答案的
    private JavaDbExhibition dbExhibition;//要显示的全部数据
    private String openType;//
    private int pageNumber=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        viewPager=(ViewPager)findViewById(R.id.read_viewPager);
        checkBox=(CheckBox)findViewById(R.id.read_cb_collect);
        toolbar=(Toolbar)findViewById(R.id.read_toolbar);
        toolbar.setTitle("");//设置标题为空
        tv_time=(TextView)findViewById(R.id.read_tv_time);
        mapAnswer=new HashMap<>();//初始化答案区
        setSupportActionBar(toolbar);//ActionBar更换为toobar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//将返回按钮设为可用
        initData_2();//初始化数据
        initView();
        findViewById(R.id.read_choice).setOnClickListener(this);
    }

    //控件初始化
    private void initView(){
        tv_btn=(TextView) findViewById(R.id.read_tv_btn);
        btnBox=(LinearLayout)findViewById(R.id.read_ll_btnBox);
        if(openType.equals(getString(R.string.type_practice))){
            btnBox.setVisibility(View.GONE);
        }else{
            btnBox.setVisibility(View.VISIBLE);
        }
        tv_btn.setOnClickListener(this);//提交按钮
        tv_correct=(TextView)findViewById(R.id.read_tv_correct);//做对的数量
        tv_mistaken=(TextView)findViewById(R.id.read_tv_mistaken);//做错的数量
        tv_progress=(TextView)findViewById(R.id.read_choice);//进度
        tv_progress.setText("1/"+dbExhibition.getNumber());//初始化当前阅读第几个
        showData(0);//更新显示第一个界面，这里要跟下面initData里面的viewPager.setCurrentItem(0);对应上
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Toast.makeText(ReadActivity.this,""+viewPager.getCurrentItem(),Toast.LENGTH_SHORT).show();
                //判断是选择题还是大题
                if(viewPager.getCurrentItem()<dbExhibition.getKuDataAggregates().getEntityList().size()){
                    threadTool.seavKuCollect(javaKuTable,dbExhibition.getKuDataAggregates().getEntityList().get(viewPager.getCurrentItem()).getId(),isChecked);//修改保存是否收藏
                    if(isChecked){
                        dbExhibition.getKuDataAggregates().getEntityList().get(viewPager.getCurrentItem()).setCollect(1);//修改显示的集合
                    }else{
                        dbExhibition.getKuDataAggregates().getEntityList().get(viewPager.getCurrentItem()).setCollect(0);
                    }
                }else{//大题
                    int num=viewPager.getCurrentItem()-dbExhibition.getKuDataAggregates().getEntityList().size();
                    threadTool.seavOperateCollect(operateTable,dbExhibition.getOperateDataAggregates().getEntityList().get(num).getId(),isChecked);//修改保存是否收藏
                    if(isChecked){
                        dbExhibition.getOperateDataAggregates().getEntityList().get(num).setCollect(1);//修改显示的集合
                    }else{
                        dbExhibition.getOperateDataAggregates().getEntityList().get(num).setCollect(0);
                    }

                }
            }
        });

        //选择启动模式
        if(openType.equals(getString(R.string.type_test))){
            downTimer.start();//启动计时器
        }else{
            tv_time.setText("练习模式");
        }
    }

    //数据初始化
    private void initData_1(){

        threadTool=new ThreadTool(this);
        javaKuTable=new JavaKuTable(this);//获取数据库java_ku的操作对象
        operateTable=new JavaOperateTable(this);
        dbExhibition=new JavaDbExhibition();
        if(!javaKuTable.isTableExits()){//判断表是否存在
            javaKuTable.creatTable(null);
            operateTable.creatTable(null);
            Gson gson=new Gson();
            FileTool fileTool=new FileTool(this);//获取文件操作类对象
            javaKuTable.addData(gson.fromJson(fileTool.getRawText(R.raw.java_ku), JavaKuDataAggregate.class));//添加数据

            operateTable.addData(gson.fromJson(fileTool.getRawText(R.raw.java_operate), JavaOperateDataAggregate.class));
        }
        dbExhibition.setKuDataAggregates(javaKuTable.queryAll());//添加选择题
        dbExhibition.setOperateDataAggregates(operateTable.queryAll());//添加填空题
        adapter=new ReadViewPagesAdapter(this,getSupportFragmentManager(),dbExhibition,openType);//初始化
        viewPager.setAdapter(adapter);//添加适配器
        viewPager.setCurrentItem(509);//默认显示第0个界面
        viewPager.addOnPageChangeListener(this);//滑动切换监听
        downTimer.start();

    }

    private void initData_2(){
        Intent intent=getIntent();//获取上一个界面传递的数据
        threadTool=new ThreadTool(this);//初始化线程工具类
        javaKuTable=new JavaKuTable(this);//获取数据库java_ku的操作对象
        operateTable=new JavaOperateTable(this);//初始化大题表操作工具
        openType=intent.getStringExtra("openType");
        Gson gson=new Gson();//
        String gsonData=intent.getStringExtra("gson");
        dbExhibition=new JavaDbExhibition();
        if(gsonData.equals("全部")){
            JavaKuDataAggregate javaKuDataAggregate=javaKuTable.queryAll();
            dbExhibition.setKuDataAggregates(javaKuDataAggregate);
        }else if(gsonData.equals("错题")){
            JavaKuDataAggregate javaKuDataAggregate=javaKuTable.queryMistaken("0");
            dbExhibition.setKuDataAggregates(javaKuDataAggregate);
        }else {
            dbExhibition=gson.fromJson(gsonData,JavaDbExhibition.class);//解析数据
        }
        adapter=new ReadViewPagesAdapter(this,getSupportFragmentManager(),dbExhibition,openType);//初始化
        viewPager.setAdapter(adapter);//添加适配器
        viewPager.setCurrentItem(0);//默认显示第0个界面
        viewPager.addOnPageChangeListener(this);//滑动切换监听
    }
    //添加菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    //按钮的点击监听
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read_choice:
                ChoiceDialog dialog=new ChoiceDialog(this,adapter.getCount(),mapAnswer);
                dialog.showDialog();
                dialog.setCallBack(new ChoiceDialog.CallBack() {
                    @Override
                    public void callBack(int position) {
                        viewPager.setCurrentItem(position);//显示第几个界面
                    }
                });
                break;
            case R.id.read_tv_btn://提交
                tuichu();
                break;
        }
    }

    //倒计时 总时间 多久激活一次onTick方法
    private CountDownTimer downTimer=new CountDownTimer(2*60*60*1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            long time=millisUntilFinished/1000;
            tv_time.setText("倒计时 "+SystemTool.getTimeString((int) time));//显示倒计时
        }
        @Override
        public void onFinish() {
            tv_time.setText("时间到！");
            showFraction();
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //页面切换完成后调用
    @Override
    public void onPageSelected(int position) {
        showData(position);//第几个界面
        pageNumber=position;
        //Toast.makeText(this,""+position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public synchronized void switchItem(){
        pageNumber++;
        viewPager.setCurrentItem(pageNumber);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，防止重新打开父Activity重走生命周期方法
            case android.R.id.home:
                tuichu();
                break;
            case R.id.menu_setting:
                Toast.makeText(this,"功能还没实现，有待完整中！",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    //刷新显示界面数据
    private void showData(int position){
        String correct;
        String mistaken;
        if(position<dbExhibition.getKuDataAggregates().getEntityList().size()){
            correct=String.valueOf(dbExhibition.getKuDataAggregates().getEntityList().get(position).getNum()-dbExhibition.getKuDataAggregates().getEntityList().get(position).getMistaken());//总数减错误的
            mistaken=String.valueOf(dbExhibition.getKuDataAggregates().getEntityList().get(position).getMistaken());
            if(dbExhibition.getKuDataAggregates().getEntityList().get(position).getCollect()==0){
                checkBox.setChecked(false);
            }else{
                checkBox.setChecked(true);
            }
        }else{
            int num=position-dbExhibition.getKuDataAggregates().getEntityList().size();
            if(dbExhibition.getOperateDataAggregates().getEntityList().get(num).getCollect()==0){
                checkBox.setChecked(false);
            }else{
                checkBox.setChecked(true);
            }
            correct=String.valueOf(dbExhibition.getOperateDataAggregates().getEntityList().get(num).getNum()-dbExhibition.getOperateDataAggregates().getEntityList().get(num).getMistaken());//总数减错误的
            mistaken=String.valueOf(dbExhibition.getOperateDataAggregates().getEntityList().get(num).getMistaken());
        }
        tv_mistaken.setText(mistaken);//做错多少条
        tv_correct.setText(correct);//做对多少条
        tv_progress.setText((position+1)+"/"+dbExhibition.getNumber());
    }

    //添加答案
    public void upData(int id, String answer) {
        mapAnswer.put(id,answer);
    }

    //点击了返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            tuichu();//弹出确定退出的提示框
        }
        return false;
    }

    //弹出确定退出的提示框，并提交分数
    private void tuichu(){
        if(openType.equals(getString(R.string.type_practice))||dbExhibition.getKuDataAggregates().getEntityList().size()==0){//没有选择题
            WarnDialog dialog=new WarnDialog(this);//创建警告提示框
            dialog.showDialog("提示！","当前为练习模式，\n是否要退出！");//显示警告提示框
            dialog.setCallBack(new WarnDialog.CallBack() {
                @Override
                public void callBack() {
                    score(mapAnswer,dbExhibition.getKuDataAggregates());//提交
                    finish();//退出
                }
            });//调用接口，处理点击确认按钮的事件
            return;
        }

        if(mapAnswer.size()==dbExhibition.getKuDataAggregates().getEntityList().size()){//判断是否答完
            showFraction();
        }else{
            WarnDialog dialog=new WarnDialog(this);//显示警告提示框
            dialog.showDialog("警告！","你还剩"+(dbExhibition.getKuDataAggregates().getEntityList().size()-mapAnswer.size())+"题没答！\n确定提交退出吗？");
            dialog.setCallBack(new WarnDialog.CallBack() {
                @Override
                public void callBack() {
                    showFraction();
                }
            });//调用接口，处理点击确认按钮的事件
        }
    }

    //显示分数
    public void showFraction(){
        WarnDialog warn=new WarnDialog(ReadActivity.this,true);//显示分数提示框
        warn.showDialog("评分","你的得分是："+score(mapAnswer,dbExhibition.getKuDataAggregates()));//计算评分并显示
        warn.setCallBack(new WarnDialog.CallBack() {
            @Override
            public void callBack() {
                finish();
            }
        });//调用接口，处理点击确认按钮的事件
    }

    //评分，并将写过的入库
    private String score(Map<Integer,String> mapScore,JavaKuDataAggregate aggregate){
        double correct=0;//记录答对了多少题
        for(int i=0;i<aggregate.getEntityList().size();i++){//循环遍历所有的选择题
            JavaKuEntity javaKuEntity = aggregate.getEntityList().get(i);//单条选择题的临时变量
            if(mapScore.containsKey(i)){//判断这个选择题是否做过
                String value=mapScore.get(i).trim();//获取选择题用户填的答案
                String answer=javaKuEntity.getAnswer().trim();//获取选择题的答案
                if(value.equals(answer)){//判断
                    threadTool.seavKuNum(javaKuTable,javaKuEntity.getId());//保存做了一次该选择题
                    correct++;//做对一次
                    continue;//跳过本次循环
                }else{
                    threadTool.seavKuNum(javaKuTable,javaKuEntity.getId());//保存做了一次该选择题
                    threadTool.seavKuMistaken(javaKuTable,javaKuEntity.getId());//保存做了错一次该选择题
                }
            }
        }
        java.text.DecimalFormat df   =new   java.text.DecimalFormat("0.00");//工具类保留两位小数用
        //correct=correct/aggregate.getEntityList().size();//计算分数
        //return df.format(correct*100);//保留两位小数用
        return df.format(correct);
    }



}