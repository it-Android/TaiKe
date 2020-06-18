package com.example.taike01.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.taike01.R;
import com.example.taike01.fragment.FragmentScreen;

//主界面
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    public static Activity activity;
    private FragmentScreen fragmentScreen;
    private RadioGroup radioGroup;
    private CallBack callBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentScreen=new FragmentScreen(getSupportFragmentManager());//初始化显示的碎片的选择器
        radioGroup=(RadioGroup)findViewById(R.id.main_rg_box);//
        radioGroup.setOnCheckedChangeListener(this);
        activity=this;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_rb_daily:
                fragmentScreen.showFragment(0);
                break;
            case R.id.main_rb_test:
                fragmentScreen.showFragment(1);
                break;
            case R.id.main_rb_mine:
                fragmentScreen.showFragment(2);
                break;
        }
    }

    private long firstTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(callBack!=null){
            callBack.onRestart();
        }
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack{
        void onRestart();
    }

}
