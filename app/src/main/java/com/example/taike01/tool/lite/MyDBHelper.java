package com.example.taike01.tool.lite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/***
 * sqlite的数据库的帮助工具
 * @author JQ
 * @date 2019/9/11
 */
public class MyDBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME="TaiKe.db";//数据库名称
    private SQLiteDatabase db;

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 打开数据库
     * @return
     */
    public SQLiteDatabase openConnection (){
        if(!db.isOpen())//判断数据库是否打开
        {
            //读写方式获取SQLiteDatabase
            db=getWritableDatabase();
        }
        return db;
    }

    /***
     * 关闭 SQLite数据库连接操作
     */
    public void closeConnection (){
        try{
            if(db!=null&&db.isOpen())//如果数据库存在并且处在打开状态，关闭数据库
                db.close();//调用close方法关闭数据库
        }catch(Exception e){
            e.printStackTrace();//将异常输出
        }
    }




    public boolean add(String tableName,String key,String idKey,int id){
        try {
            openConnection();
            String sql="update "+tableName+" set "+key+"="+key+"+1 " +"where "+idKey+"="+id;
            db.execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally{
            closeConnection();//关闭数据库
        }
        return true;
    }



    /**
     * 创建表
     * @param createTableSql
     * @return 是否成功
     */
    public  boolean creatTable( String  createTableSql){
        try{
            openConnection();//打开数据库
            db.execSQL(createTableSql); //调用创建表SQL语句
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }finally{
            closeConnection();//关闭数据库
        }
        return true;
    }

    /**
     * 添加操作
     * @param tableName   表名
     * @param values      集合对象
     * @return 是否成功
     */
    public  boolean  save(String tableName, ContentValues values ){
        try{
            openConnection();//打开数据库
            db.insert(tableName, null, values);//调用insert方法插入一行数据到表中

        }catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }finally{
            closeConnection();//关闭数据库
        }
        return true;
    }

    /**
     * 更新操作
     * @param table
     * @param values
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public  boolean  update(String table,ContentValues values ,String whereClause,String []whereArgs){
        try{
            openConnection();
            db.update(table, values, whereClause, whereArgs);//调用SQLiteDatabase类中的update方法
        }catch(Exception ex){
            ex.printStackTrace();
            return  false;
        }finally{
            closeConnection();
        }
        return true;
    }

    /**
     *  删除
     * @param table 表名
     * @param deleteSql 对应跟新字段
     * @param obj   对应值
     *               如：   new Object[]{person.getPersonid()};
     * @return
     */
    public  boolean  delete(String table,String deleteSql,String obj[])
    {
        try{
            openConnection();
            db.delete(table, deleteSql, obj);//调用SQLiteDatabase类中的delete方法

        }catch(Exception ex)
        {
            ex.printStackTrace();
            return  false;
        }finally
        {
            closeConnection();
        }
        return true;
    }
    /**
     * 查询操作
     * @param findSql  对应查询字段    如：
     * select * from person limit ?,?
     * @param obj      对应值                   如：
     *  new String[]{String.valueOf(fristResult)
     *  ,String.valueOf(maxResult)}
     * @return
     */
    public Cursor find(String findSql, String obj[]  ) {
        try{
            openConnection();
            Cursor cursor = db.rawQuery(findSql,obj);
            return  cursor;

        }catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }




    //查询全部
    public Cursor findAll(String tableName){
        try {
            openConnection();
            Cursor cursor=db.query(tableName,null,null,null,null,null,null);
            return cursor;
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }


    /**
     * 判断表是否存在
     * @param tablename
     * @return
     */
    public boolean isTableExits( String tablename){
        try{
            openConnection();
            String str="select count(*) xcount  from  "
                    +tablename;
            db.rawQuery(str,null).close();
        }catch(Exception ex){
            return false;
        }finally{
            closeConnection();
        }
        return true;
    }
}
