package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE, null, VERSION) {
    companion object{
        const val DATABASE = "SQLa.db"
        const val VERSION = 1
        const val TABLE = "taz12"
        const val ID = "id"
        const val NAME = "name"
        const val DATE = "date"
        const val ADDRESS = "address"
        const val GIOITINH = "gioittinh"
        const val TRUONG_CNTT = "truong_cntt"
        const val TRUONG_KINHTE = "truong_kinhte"
        const val TRUONG_SUPHAM = "truong_supham"
        const val CREATE_TABLE  =
            " CREATE TABLE " + TABLE + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    DATE + " TEXT, " +
                    ADDRESS + " TEXT, " +
                    GIOITINH + " TEXT, " +
                    TRUONG_CNTT + " INTEGER, " +
                    TRUONG_KINHTE + " INTEGER, " +
                    TRUONG_SUPHAM + " INTEGER " +");"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun insertUser(user: User){
        val db = writableDatabase
        val values = ContentValues()
        values.put(NAME, user.name)
        values.put(DATE, user.date)
        values.put(ADDRESS, user.address)
        values.put(GIOITINH, user.gioitinh)
        values.put(TRUONG_CNTT, if(user.truong_cntt) 1 else 0)
        values.put(TRUONG_KINHTE, if(user.truong_kinhte) 1 else 0)
        values.put(TRUONG_SUPHAM, if(user.truong_supham) 1 else 0)
        db.close()
    }
    fun getAllUser(): MutableList<User>{
        val list = mutableListOf<User>()
        val db = readableDatabase
        val cursor = db.query(TABLE, null,null,null,null,null,null)
        if(cursor.moveToFirst()){
            do {
                val user = User()
                user.id = cursor.getInt(0)
                user.name = cursor.getString(1)
                user.date = cursor.getString(2)
                user.address = cursor.getString(3)
                user.gioitinh = cursor.getString(4)
                user.truong_cntt = cursor.getInt(5) == 1
                user.truong_kinhte = cursor.getInt(6) == 1
                user.truong_supham = cursor.getInt(7) == 1
                list.add(user)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }
}