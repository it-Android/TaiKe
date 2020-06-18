package com.example.taike01.tool;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * getSharedPreferences 操作工具
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/4 10:40
 **/
public class SpTool {
    private final static String SP_NAME="setting";//sp库名称
    public final static String SP_HEAD_ID="headId";

    private static SharedPreferences getSp(Context context){
        return context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEd(Context context){
        return getSp(context).edit();
    }

    /**
     * 添加字符串数据
     * @param context 上下文对象
     * @param key 要添加的键
     * @param value 要添加的值
     */
    public static void append(Context context,String key,String value){
        SharedPreferences.Editor editor=getEd(context);
        editor.putString(key,value);
        editor.commit();
    }

    /**
     * 添加int数据
     * @param context 上下文对象
     * @param key 要添加的键
     * @param value 要添加的值
     */
    public static void append(Context context,String key,int value){
        SharedPreferences.Editor editor=getEd(context);
        editor.putInt(key,value);
        editor.commit();
    }

    /**
     * 添加map结合
     * @param context 上下文对象
     * @param map 将map里面的键与值对应写入
     */
    public static  void  append(Context context, Map<String,String> map){
        if (map==null||map.size()==0)return;
        SharedPreferences.Editor editor=getEd(context);
        for(Map.Entry<String,String> entry:map.entrySet()){//for循环遍历map
            editor.putString(entry.getKey(),entry.getValue());
        }
        editor.commit();
    }
    /**
     *  添加布尔数据
     * @param context 上下文对象
     * @param key 键
     * @param value 值
     */
    public static void append(Context context,String key,Boolean value){
        SharedPreferences.Editor editor=getEd(context);
        editor.putBoolean(key,value);
        editor.commit();
    }

    /**
     *  读取字符串数据
     * @param context 上下文对象
     * @param key 键
     * @return 返回读取到的值 没有则返回空格
     */
    public static String readString(Context context,String key){
        SharedPreferences sp=getSp(context);
        String data="";
        try{
            data=sp.getString(key,"");
        }catch (Exception e){
            data="";
        }
        return data;
    }

    public int readInt(Context context,String key){
        SharedPreferences sp=getSp(context);
        try{
            return sp.getInt(key,0);
        }catch (Exception e){
            return 0;
        }
    }
    /**
     *  获取某个键的布尔值
     * @param context 上下文对象
     * @param key 键
     * @return 返回读取到的booleam 没有则返回false
     */
    public static Boolean readBoolean(Context context,String key){
        SharedPreferences sp=getSp(context);
        Boolean data=false;
        try {
            data=sp.getBoolean(key,false);
        }catch (Exception e){
            data=false;
        }
        return data;
    }
}
