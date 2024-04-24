package com.example.dorstip_app.dashboard

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper



class DataBaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "DrinksDB"
        private const val TABLE_DRINKS = "drinks"

        private const val COL_ID = "_id"
        private const val COL_NAME = "name"
        private const val COL_IMAGE = "image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_DRINKS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT," +
                COL_IMAGE + " TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // controleren of de tabel bestaay
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRINKS)
        onCreate(db)
    }

    fun addDrinksCategory(cat: Category): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(COL_NAME, cat.name)
        contentValues.put(COL_IMAGE, cat.image)

        val success = db.insert(TABLE_DRINKS, null, contentValues)

        db.close()
        return success
    }

    fun viewDrinksCategory(): ArrayList<Category> {
        val catList: ArrayList<Category> = ArrayList()

        val selectQuery = "SELECT * FROM $TABLE_DRINKS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var image: String

        cursor?.use {
            while (it.moveToNext()) {
                id = it.getInt(it.getColumnIndex(COL_ID))
                name = it.getString(it.getColumnIndex(COL_NAME))
                image = it.getString(it.getColumnIndex(COL_IMAGE))

                val cat = Category(id = id, name = name, image = image)
                catList.add(cat)
            }
        }

        return catList
    }

    fun updateDrinkCategory(cat: Category): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, cat.name)
        contentValues.put(COL_IMAGE, cat.image)

        val success = db.update(TABLE_DRINKS, contentValues, "$COL_ID = ?", arrayOf(cat.id.toString()))

        db.close()
        return success
    }

    fun deleteDrinkCategory(cat: Category): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_DRINKS, "$COL_ID = ?", arrayOf(cat.id.toString()))
        db.close()
        return success
    }


}









