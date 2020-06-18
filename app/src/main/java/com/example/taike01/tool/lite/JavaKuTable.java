package com.example.taike01.tool.lite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.taike01.entityClass.JavaKuDataAggregate;
import com.example.taike01.entityClass.JavaKuEntity;


/**
 * java_ku的操作类
 *
 * @作者(author)： JQ
 * @创建时间(date)： 2019/10/2 13:55
 **/
public class JavaKuTable {
    private MyDBHelper db;
    private final static String TABLE_NAME="java_ku";

    public JavaKuTable(Context context){
        db=new MyDBHelper(context);
        //creatTable(TABLE_NAME);
    }
    //创建表创建Java_ku表
    public synchronized void creatTable(String tableName){
        if(tableName==null||tableName.equals(""))tableName=TABLE_NAME;
        if(!db.isTableExits(tableName)){
            String sql="CREATE TABLE IF NOT EXISTS " +tableName+"( "+
                    JavaKuEntity.ID+" integer PRIMARY KEY,"+
                    JavaKuEntity.TITLE +"  VARCHAR,"+
                    JavaKuEntity.OPTIONA +"  VARCHAR,"+
                    JavaKuEntity.OPTIONB +"  VARCHAR,"+
                    JavaKuEntity.OPTIONC +"  VARCHAR,"+
                    JavaKuEntity.OPTIOND +"  VARCHAR,"+
                    JavaKuEntity.ANSWER +"  VARCHAR,"+
                    JavaKuEntity.EXPLAIN +"  VARCHAR,"+
                    JavaKuEntity.TYPE +"  VARCHAR,"+
                    JavaKuEntity.NUM +" INTEGER,"+
                    JavaKuEntity.MISTAKEN +" INTEGER,"+
                    JavaKuEntity.COLLECT +" INTEGER )";
            db.creatTable(sql);
        }
    }

    /**
     *  num加一次
     * @param id
     */
    public synchronized Boolean addNum(int id){
        return db.add(TABLE_NAME,JavaKuEntity.NUM,JavaKuEntity.ID,id);
    }

    /**
     *  Mistaken加一次
     * @param id
     */
    public synchronized Boolean addMistaken(int id){
        return db.add(TABLE_NAME,JavaKuEntity.MISTAKEN,JavaKuEntity.ID,id);
    }


    //添加单行数据
    public synchronized Boolean appData(JavaKuEntity kuEntity) {
        ContentValues values = new ContentValues();
        values.put(JavaKuEntity.ID,kuEntity.getId());
        values.put(JavaKuEntity.TITLE,kuEntity.getTitle());
        values.put(JavaKuEntity.OPTIONA,kuEntity.getOptionA());
        values.put(JavaKuEntity.OPTIONB,kuEntity.getOptionB());
        values.put(JavaKuEntity.OPTIONC,kuEntity.getOptionC());
        values.put(JavaKuEntity.OPTIOND,kuEntity.getOptionD());
        values.put(JavaKuEntity.ANSWER,kuEntity.getAnswer());
        values.put(JavaKuEntity.EXPLAIN,kuEntity.getExplain());
        values.put(JavaKuEntity.TYPE,kuEntity.getType());
        values.put(JavaKuEntity.NUM,kuEntity.getNum());
        values.put(JavaKuEntity.MISTAKEN,kuEntity.getMistaken());
        values.put(JavaKuEntity.COLLECT,kuEntity.getCollect());
        return db.save(TABLE_NAME,values);
    }

    //添加一堆数据
    public synchronized void addData(JavaKuDataAggregate kuDataAggregate){
        if(kuDataAggregate==null)return;
        SQLiteDatabase database=db.openConnection();
        try{
            String sql = "insert into "+TABLE_NAME+" ("
                    + JavaKuEntity.ID+","
                    + JavaKuEntity.TITLE+","
                    + JavaKuEntity.OPTIONA+","
                    + JavaKuEntity.OPTIONB+","
                    + JavaKuEntity.OPTIONC+","
                    + JavaKuEntity.OPTIOND+","
                    + JavaKuEntity.ANSWER+","
                    + JavaKuEntity.EXPLAIN+","
                    + JavaKuEntity.TYPE+","
                    + JavaKuEntity.NUM+","
                    + JavaKuEntity.MISTAKEN+","
                    + JavaKuEntity.COLLECT+" )"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
            SQLiteStatement stat = database.compileStatement(sql);
            //加上的代码(三行代码，速度快了40倍)
            database.beginTransaction();//第一行
            for(JavaKuEntity kuEntity:kuDataAggregate.getEntityList()){
                stat.bindLong(1,kuEntity.getId());
                stat.bindString(2,kuEntity.getTitle());
                stat.bindString(3,kuEntity.getOptionA());
                stat.bindString(4,kuEntity.getOptionB());
                stat.bindString(5,kuEntity.getOptionC());
                stat.bindString(6,kuEntity.getOptionD());
                stat.bindString(7,kuEntity.getAnswer());
                stat.bindString(8,kuEntity.getExplain());
                stat.bindString(9,kuEntity.getType());
                stat.bindLong(10,kuEntity.getNum());
                stat.bindLong(11,kuEntity.getMistaken());
                stat.bindLong(12,kuEntity.getCollect());
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
    public synchronized JavaKuDataAggregate queryAll(){
        Cursor cursor = db.findAll(TABLE_NAME);
        if(cursor==null)return null;
        return getDBdata(cursor);
    }

    //读取java_ku Type的数据 查询type的
    public synchronized JavaKuDataAggregate queryType(String typeData){
        String sql="select * from "+TABLE_NAME+" where "+JavaKuEntity.TYPE+" =?";
        Cursor cursor = db.find(sql, new String[]{typeData});
        return getDBdata(cursor);
    }

    //查询收藏 0 ,为收藏，1 收藏 ，别输入其他
    public synchronized JavaKuDataAggregate queryCollect(String collectNumber){
        if(collectNumber==null||collectNumber.equals(""))collectNumber="1";
        String sql="select * from "+TABLE_NAME+" where "+JavaKuEntity.COLLECT+" =?";
        Cursor cursor = db.find(sql, new String[]{collectNumber});
        return getDBdata(cursor);
    }

    //查询错题    mistakenNumber 错题的次数
    public synchronized JavaKuDataAggregate queryMistaken(String mistakenNumber){
        String sql="select * from "+TABLE_NAME+" where "+JavaKuEntity.MISTAKEN+" > ?";
        Cursor cursor = db.find(sql, new String[]{mistakenNumber});
        return getDBdata(cursor);
    }


    /**
     *  修改做过多少次
     * @param id 索引id
     * @param num 做过多少次，要改成的次数
     * @return 是否成功
     */
    public synchronized Boolean updataNum(int id,int num){
        ContentValues values=new ContentValues();
        values.put(JavaKuEntity.NUM,num);
        return db.update(TABLE_NAME,values,"id=?",new String[]{""+id});
    }

    /**
     *  修改 做错了多少次
     * @param id 索引id
     * @param mistakenNum 做过错次数，要改成的次数
     * @return 是否修改成功
     */
    public synchronized Boolean updataMistaken(int id,int mistakenNum){
        ContentValues values=new ContentValues();
        values.put(JavaKuEntity.MISTAKEN,mistakenNum);
        return db.update(TABLE_NAME,values,"id=?",new String[]{""+id});
    }

    /**
     * 修改 是否收藏
     * @param id 索引id
     * @param collectNum 0 取消收藏、 1 记录收藏
     * @return
     */
    public synchronized Boolean updataCollect(int id,int collectNum){
        ContentValues values=new ContentValues();
        values.put(JavaKuEntity.COLLECT,collectNum);
        return db.update(TABLE_NAME,values,"id=?",new String[]{""+id});
    }


    //判断表是否存在
    public synchronized Boolean isTableExits(){
        return db.isTableExits(TABLE_NAME);
    }



    //解析游标    返回 JavaKuDataAggregate
    private synchronized JavaKuDataAggregate getDBdata(Cursor cursor){
        JavaKuDataAggregate kuDataAggregate=new JavaKuDataAggregate();
        while (cursor.moveToNext()){
            JavaKuEntity kuEntity=new JavaKuEntity();
            kuEntity.setId(cursor.getInt(cursor.getColumnIndex(JavaKuEntity.ID)));
            kuEntity.setTitle(cursor.getString(cursor.getColumnIndex(JavaKuEntity.TITLE)));
            kuEntity.setOptionA(cursor.getString(cursor.getColumnIndex(JavaKuEntity.OPTIONA)));
            kuEntity.setOptionB(cursor.getString(cursor.getColumnIndex(JavaKuEntity.OPTIONB)));
            kuEntity.setOptionC(cursor.getString(cursor.getColumnIndex(JavaKuEntity.OPTIONC)));
            kuEntity.setOptionD(cursor.getString(cursor.getColumnIndex(JavaKuEntity.OPTIOND)));
            kuEntity.setAnswer(cursor.getString(cursor.getColumnIndex(JavaKuEntity.ANSWER)));
            kuEntity.setExplain(cursor.getString(cursor.getColumnIndex(JavaKuEntity.EXPLAIN)));
            kuEntity.setType(cursor.getString(cursor.getColumnIndex(JavaKuEntity.TYPE)));
            kuEntity.setNum(cursor.getInt(cursor.getColumnIndex(JavaKuEntity.NUM)));
            kuEntity.setMistaken(cursor.getInt(cursor.getColumnIndex(JavaKuEntity.MISTAKEN)));
            kuEntity.setCollect(cursor.getInt(cursor.getColumnIndex(JavaKuEntity.COLLECT)));
            kuDataAggregate.getEntityList().add(kuEntity);
        }
        return kuDataAggregate;
    }

}
