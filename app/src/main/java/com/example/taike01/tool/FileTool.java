package com.example.taike01.tool;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 文件操作工具类
 *
 *
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/2 13:43
 **/
public class FileTool {

    private Context context;

    public FileTool(Context context) {
        this.context = context;
    }


    /**
     * 读取raw里面的txt文本数据
     * @param txtData
     * @return
     */
    public String getRawText(int txtData){
        Resources res=context.getResources();
        InputStream in=null;
        InputStreamReader inr= null;
        BufferedReader reader=null;
        StringBuffer buffer=new StringBuffer();
        try {
            in=res.openRawResource(txtData);//读取raw里面的txt文本数据
            inr = new InputStreamReader(in,"UTF-8");
            reader=new BufferedReader(inr);
            String line="";
            while ((line=reader.readLine())!=null){
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inr!=null){
                try {
                    inr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }


    /** 只能mipmap
     * @param name
     * @return
     */
    public int getResId(String name) {
        Resources r=context.getResources();
        int id = r.getIdentifier(name, "mipmap", context.getPackageName());
        return id;
    }

    /**
     * 获取设置的头像
     * @return
     */
    public int getHeadImage(){
        String imageId=SpTool.readString(context,SpTool.SP_HEAD_ID);//获取头像的ID
        if(imageId.equals("")){imageId="tx2";}//假如不存在就显示默认头像
        return getResId(imageId);
    }

    /**
     * 获取指定名称的头像
     * @param imgName
     * @return
     */
    public int getHeadImage(String imgName){
        //String imageId=SpTool.readString(context,SpTool.SP_HEAD_ID);//获取头像的ID
        if(imgName.equals("")){imgName="tx2";}//假如不存在就显示默认头像
        return getResId(imgName);
    }
}
