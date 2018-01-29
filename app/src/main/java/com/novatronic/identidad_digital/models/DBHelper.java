package com.novatronic.identidad_digital.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DBHelper extends OrmLiteSqliteOpenHelper {


    public static final String DB_NAME = "database.db";
    private static final int DB_VERSION = 1;

    private Dao<User, Integer> userDao;
    private Dao<Point, Integer> pointDao;
    private Dao<History, Integer> historyDao;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
        try {
            TableUtils.createTable(cs, User.class);
            TableUtils.createTable(cs, Point.class);
            TableUtils.createTable(cs, History.class);
            ContentValues cv = new ContentValues();
            cv.put("first_name","Hansy");
            cv.put("last_name_p","Schmitt");
            cv.put("last_name_m","Camacho");
            cv.put("dni","43186729");
            cv.put("pin","12345");
            cv.put("kind","gestor");
            db.insert("users",null,cv);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    public Dao<Point, Integer> getPointDao() throws SQLException {
        if (pointDao == null) {
            pointDao = getDao(Point.class);
        }
        return pointDao;
    }
    public Dao<History, Integer> getHistoryDao() throws SQLException {
        if (historyDao == null) {
            historyDao = getDao(History.class);
        }
        return historyDao;
    }
    @Override
    public void close() {
        super.close();
        userDao = null;
        pointDao = null;
        historyDao=null;
    }
}