package xgq.student.gdmec.work4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/11/1 0001.
 */
public class MyDB extends SQLiteOpenHelper {
    private static String DB_NAME="My_DB.db";
    private static int DB_VERSION=2;
    private SQLiteDatabase db;
    // 构造方法
    public MyDB(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        db=getWritableDatabase();
    }
    // 连接数据库
    public SQLiteDatabase openConnection(){
        if(!db.isOpen()){
            db=getWritableDatabase();
        }
        return db;
    }
    // 关闭数据库
    public void closeConnection(){
        try{
            if(db!=null&&db.isOpen())
                db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 创建数据表
    public boolean createTable(String createTableSql){
        try {
            openConnection();
            db.execSQL(createTableSql);
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    // 保存数据
    public boolean save(String tableName,ContentValues values){
        try {
            openConnection();
            db.insert(tableName, null, values);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    // 更新数据
    public boolean update(String table,ContentValues values,String whereClause,String[] whereArgs){
        try {
            openConnection();
            db.update(table, values, whereClause, whereArgs);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    // 删除数据
    public boolean delete(String table,String deleteSql,String obj[]){
        try {
            openConnection();
            db.delete(table, deleteSql, obj);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }finally {
            closeConnection();
        }
        return true;
    }
    // 查找数据
    public Cursor find(String findSql,String obj[]){

        try {
            openConnection();
            Cursor cursor = db.rawQuery(findSql,obj);
            return  cursor;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    // 检查数据表是否存在
    public boolean isTableExits(String tableName){
        try {
            openConnection();
            String str="select count(*)xcount from "+tableName;
            db.rawQuery(str,null).close();
        }catch(Exception e){
            return false;
        }
        return true;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        // TODO 每次成功打开数据库后首先执行
        super.onOpen(db);
    }
}
