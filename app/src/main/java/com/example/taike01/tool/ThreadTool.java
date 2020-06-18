package com.example.taike01.tool;

import android.content.Context;

import com.example.taike01.tool.lite.JavaKuTable;
import com.example.taike01.tool.lite.JavaOperateTable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  线程处理工具
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/5 12:06
 **/
public class ThreadTool {
    private Context context;
    private ExecutorService dbService;

    public ThreadTool(Context context) {
        this.context = context;
    }

    //保存收藏
    public  void seavKuCollect(final JavaKuTable kuTable, final int id, final Boolean isCheck){
        if(dbService==null)dbService= Executors.newFixedThreadPool(5);
        dbService.execute(new Runnable() {
            @Override
            public void run() {
                if(isCheck){
                    kuTable.updataCollect(id,1);
                }else {
                    kuTable.updataCollect(id,0);
                }
            }
        });
    }

    //将保存加一次
    public void seavKuNum(final JavaKuTable kuTable, final int id){
        if(dbService==null)dbService= Executors.newFixedThreadPool(5);
        dbService.execute(new Runnable() {
            @Override
            public void run() {
                kuTable.addNum(id);
            }
        });
    }

    //将错误加一次
    public void seavKuMistaken(final JavaKuTable kuTable, final int id){
        if(dbService==null)dbService= Executors.newFixedThreadPool(5);
        dbService.execute(new Runnable() {
            @Override
            public void run() {
                kuTable.addMistaken(id);
            }
        });
    }


    public void seavOperateCollect(final JavaOperateTable operateTable, final int id, final Boolean isCheck){
        if(dbService==null)dbService= Executors.newFixedThreadPool(5);
        dbService.execute(new Runnable() {
            @Override
            public void run() {
                if(isCheck){
                    operateTable.updataCollect(id,1);
                }else {
                    operateTable.updataCollect(id,0);
                }
            }
        });
    }
}
