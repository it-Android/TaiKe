package com.example.taike01.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.taike01.R;

/**
 * fragment分拣器，实现简化activity界面的代码，和快速更换fragment
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/3 22:47
 **/
public class FragmentScreen {
    private FragmentManager manager;
    private DailyFragment df;
    private TestFragment tf;
    private MineFragment mf;

    public FragmentScreen(FragmentManager manager) {
        this.manager = manager;
        df=new DailyFragment();
        tf=new TestFragment();
        mf=new MineFragment();
        manager.beginTransaction()//初始化添加碎片
                .add(R.id.main_fl, df)//add 添加碎片
                .commit();
    }
    /**
     *  显示新的碎片
     * @param num 要显示那个碎片
     * @return 返回显示的碎片
     */
    public Fragment showFragment(int num){
        Fragment fragment=null;
        switch (num){
            case 0:
                fragment=df;
                break;
            case 1:
                fragment=tf;
                break;
            case 2:
                fragment=mf;
                break;
            default: fragment=df;
        }
        manager.beginTransaction()//初始化添加碎片
                .replace(R.id.main_fl, fragment)//add 添加碎片
                .commit();
        return fragment;
    }

}
