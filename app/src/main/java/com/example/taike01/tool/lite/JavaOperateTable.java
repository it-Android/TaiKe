package com.example.taike01.tool.lite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.taike01.entityClass.JavaOperateDataAggregate;
import com.example.taike01.entityClass.JavaOperateEntity;


/**
 *
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/5 10:47
 **/
public class JavaOperateTable {
    private MyDBHelper db;
    private final static String TABLE_NAME="java_operate";
    private final static String TAG="JavaOperateTable数据监听";

    public JavaOperateTable(Context context) {
       db=new MyDBHelper(context);
        //creatTable(TABLE_NAME);
    }


    public void creatTable(String tableName) {
        if(tableName==null||tableName.equals("")) tableName=TABLE_NAME;
        if (!db.isTableExits(tableName)) {
            String sql="CREATE TABLE IF NOT EXISTS " +tableName+"( "+
                    JavaOperateEntity.ID +" integer PRIMARY KEY,"+
                    JavaOperateEntity.TITLE +"  VARCHAR,"+
                    JavaOperateEntity.MATERIAL +"  VARCHAR,"+
                    JavaOperateEntity.ANSWER +"  VARCHAR,"+
                    JavaOperateEntity.PHOTO +"  VARCHAR,"+
                    JavaOperateEntity.TYPE +"  VARCHAR,"+
                    JavaOperateEntity.NUM +" INTEGER,"+
                    JavaOperateEntity.MISTAKEN +" INTEGER,"+
                    JavaOperateEntity.COLLECT +" INTEGER )";
            if(!db.creatTable(sql)) {
                Log.e(TAG,"表创建失败！");
            }
        }
    }


    //添加单行数据
    public Boolean appData(JavaOperateEntity operateEntity) {
        ContentValues values = new ContentValues();
        values.put(JavaOperateEntity.ID,operateEntity.getId());
        values.put(JavaOperateEntity.TITLE,operateEntity.getTitle());
        values.put(JavaOperateEntity.MATERIAL,operateEntity.getMaterial());
        values.put(JavaOperateEntity.ANSWER,operateEntity.getAnswer());
        values.put(JavaOperateEntity.PHOTO,operateEntity.getPhoto());
        values.put(JavaOperateEntity.TYPE,operateEntity.getType());
        values.put(JavaOperateEntity.NUM,operateEntity.getNum());
        values.put(JavaOperateEntity.MISTAKEN,operateEntity.getMistaken());
        values.put(JavaOperateEntity.COLLECT,operateEntity.getCollect());
        return db.save(TABLE_NAME,values);
    }


    /**
     * 添加大量数据比较快
     * @param operateDataAggregate JavaOperateDataAggregate 的数据集合
     */
    public void addData(JavaOperateDataAggregate operateDataAggregate){
        if(operateDataAggregate==null)return;
        SQLiteDatabase database=db.openConnection();
        try{
            String sql = "insert into "+TABLE_NAME+" ("
                    + JavaOperateEntity.ID+","
                    + JavaOperateEntity.TITLE+","
                    + JavaOperateEntity.MATERIAL+","
                    + JavaOperateEntity.ANSWER+","
                    + JavaOperateEntity.PHOTO+","
                    + JavaOperateEntity.TYPE+","
                    + JavaOperateEntity.NUM+","
                    + JavaOperateEntity.MISTAKEN+","
                    + JavaOperateEntity.COLLECT+" )"
                    + "values(?,?,?,?,?,?,?,?,?)";
            SQLiteStatement stat = database.compileStatement(sql);
            //加上的代码(三行代码，速度快了40倍)
            database.beginTransaction();//第一行
            for(JavaOperateEntity operateEntity:operateDataAggregate.getEntityList()){
                stat.bindLong(1,operateEntity.getId());
                stat.bindString(2,operateEntity.getTitle());
                stat.bindString(3,operateEntity.getMaterial());
                stat.bindString(4,operateEntity.getAnswer());
                stat.bindString(5,operateEntity.getPhoto());
                stat.bindString(6,operateEntity.getType());
                stat.bindLong(7,operateEntity.getNum());
                stat.bindLong(8,operateEntity.getMistaken());
                stat.bindLong(9,operateEntity.getCollect());
                long result = stat.executeInsert();
            }
            database.setTransactionSuccessful();//加上的代码 第二行
        }catch (Exception e){

        }finally {
            if(db!=null)
                database.endTransaction();//加上的代码 第三行
            db.closeConnection();//关闭数据库
        }
    }


    //读取java_ku 全部数据
    public JavaOperateDataAggregate queryAll(){
        Cursor cursor = db.findAll(TABLE_NAME);
        if(cursor==null)return null;
        return getDBdata(cursor);
    }

    //读取java_ku Type的数据 查询type的
    public JavaOperateDataAggregate queryType(String typeData){
        String sql="select * from "+TABLE_NAME+" where "+JavaOperateEntity.TYPE+" =?";
        Cursor cursor = db.find(sql, new String[]{typeData});
        return getDBdata(cursor);
    }

    //查询收藏 0 ,为收藏，1 收藏 ，别输入其他
    public JavaOperateDataAggregate queryCollect(String collectNumber){
        if(collectNumber==null||collectNumber.equals(""))collectNumber="1";
        String sql="select * from "+TABLE_NAME+" where "+JavaOperateEntity.COLLECT+" =?";
        Cursor cursor = db.find(sql, new String[]{collectNumber});
        return getDBdata(cursor);
    }

    //查询错题    mistakenNumber 错题的次数
    public JavaOperateDataAggregate queryMistaken(String mistakenNumber){
        String sql="select * from "+TABLE_NAME+" where "+JavaOperateEntity.MISTAKEN+" =?";
        Cursor cursor = db.find(sql, new String[]{mistakenNumber});
        return getDBdata(cursor);
    }

    /**
     *  修改做过多少次
     * @param id 索引id
     * @param num 做过多少次，要改成的次数
     * @return 是否成功
     */
    public Boolean updataNum(int id,int num){
        ContentValues values=new ContentValues();
        values.put(JavaOperateEntity.NUM,num);
        return db.update(TABLE_NAME,values,"id=?",new String[]{""+id});
    }

    /**
     *  修改 做错了多少次
     * @param id 索引id
     * @param mistakenNum 做过错次数，要改成的次数
     * @return 是否修改成功
     */
    public Boolean updataMistaken(int id,int mistakenNum){
        ContentValues values=new ContentValues();
        values.put(JavaOperateEntity.MISTAKEN,mistakenNum);
        return db.update(TABLE_NAME,values,"id=?",new String[]{""+id});
    }

    /**
     * 修改 是否收藏
     * @param id 索引id
     * @param collectNum 0 取消收藏、 1 记录收藏
     * @return
     */
    public Boolean updataCollect(int id,int collectNum){
        ContentValues values=new ContentValues();
        values.put(JavaOperateEntity.COLLECT,collectNum);
        return db.update(TABLE_NAME,values,"id=?",new String[]{""+id});
    }



    /**
     *  将游标传入解析游标
     * @param cursor 传入的游标
     * @return 返回 JavaOperateDataAggregate 数据集
     */
    private JavaOperateDataAggregate getDBdata(Cursor cursor){
        JavaOperateDataAggregate kuDataAggregate=new JavaOperateDataAggregate();
        while (cursor.moveToNext()){
            JavaOperateEntity kuEntity=new JavaOperateEntity();
            kuEntity.setId(cursor.getInt(cursor.getColumnIndex(JavaOperateEntity.ID)));
            kuEntity.setTitle(cursor.getString(cursor.getColumnIndex(JavaOperateEntity.TITLE)));
            kuEntity.setMaterial(cursor.getString(cursor.getColumnIndex(JavaOperateEntity.MATERIAL)));
            kuEntity.setAnswer(cursor.getString(cursor.getColumnIndex(JavaOperateEntity.ANSWER)));
            kuEntity.setPhoto(cursor.getString(cursor.getColumnIndex(JavaOperateEntity.PHOTO)));
            kuEntity.setType(cursor.getString(cursor.getColumnIndex(JavaOperateEntity.TYPE)));
            kuEntity.setNum(cursor.getInt(cursor.getColumnIndex(JavaOperateEntity.NUM)));
            kuEntity.setMistaken(cursor.getInt(cursor.getColumnIndex(JavaOperateEntity.MISTAKEN)));
            kuEntity.setCollect(cursor.getInt(cursor.getColumnIndex(JavaOperateEntity.COLLECT)));
            kuDataAggregate.getEntityList().add(kuEntity);
        }
        return kuDataAggregate;
    }

}
