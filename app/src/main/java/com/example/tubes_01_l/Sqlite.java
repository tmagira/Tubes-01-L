package com.example.tubes_01_l;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tubes_01_l.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class Sqlite extends SQLiteOpenHelper {


    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.

    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "Daftar Menu";

    // table name
    private static final String TABLE_MENU = "Menu";

    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESKRIPSI = "deskripsi";
    private static final String KEY_TAG = "tag";
    private static final String KEY_BAHAN = "bahan";
    private static final String KEY_LANGKAH = "langkah";
    private static final String KEY_RESTO = "resto";

    public Sqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Sqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_MENU + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_DESKRIPSI + " TEXT," + KEY_TAG + " TEXT," + KEY_BAHAN + " TEXT," + KEY_LANGKAH + " TEXT," + KEY_RESTO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        onCreate(db);
    }

    public void addRecord(Menu Menu) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, Menu.getTitle());
        values.put(KEY_DESKRIPSI, Menu.getDeskripsi());
        values.put(KEY_TAG, Menu.getTag());
        values.put(KEY_BAHAN, Menu.getBahan());
        values.put(KEY_LANGKAH, Menu.getLangkahMasak());
        values.put(KEY_RESTO, Menu.getResto());
        db.insert(TABLE_MENU, null, values);
        db.close();
    }

    public Menu getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MENU, new String[]{KEY_ID,
                        KEY_TITLE, KEY_DESKRIPSI, KEY_TAG, KEY_BAHAN, KEY_LANGKAH, KEY_RESTO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Menu contact = new Menu(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3).split(","), cursor.getString(4).split(","), cursor.getString(5).split(","), cursor.getString(6).split(","));
        // return contact
        return contact;
    }

    //update record
    public int updateContact(Menu contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, contact.getTitle());
        values.put(KEY_DESKRIPSI, contact.getDeskripsi());
        values.put(KEY_TAG,contact.getTag());
        values.put(KEY_BAHAN,contact.getBahan());
        values.put(KEY_LANGKAH,contact.getLangkahMasak());
        values.put(KEY_RESTO,contact.getResto());
        // updating row
        return db.update(TABLE_MENU, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }

    // get All Record
    public List<Menu> getAllRecord() {
        List<Menu> contactList = new ArrayList<Menu>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MENU;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Menu Menu = new Menu();
                Menu.setId(Integer.parseInt(cursor.getString(0)));
                Menu.setTitle(cursor.getString(1));
                Menu.setDeskripsi(cursor.getString(2));
                Menu.setTag(cursor.getString(3).split(","));
                Menu.setBahan(cursor.getString(4).split(","));
                Menu.setLangkahMasak(cursor.getString(5).split(","));
                Menu.setResto(cursor.getString(6).split(","));
                contactList.add(Menu);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public List<Menu> getFilteredRecord(String tag) {
        List<Menu> contactList = new ArrayList<Menu>();

        String selectQuery = "SELECT  * FROM " + TABLE_MENU + " WHERE "+ KEY_TITLE +" LIKE '%" + tag + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Menu Menu = new Menu();
                Menu.setId(Integer.parseInt(cursor.getString(0)));
                Menu.setTitle(cursor.getString(1));
                Menu.setDeskripsi(cursor.getString(2));
                Menu.setTag(cursor.getString(3).split(","));
                Menu.setBahan(cursor.getString(4).split(","));
                Menu.setLangkahMasak(cursor.getString(5).split(","));
                Menu.setResto(cursor.getString(6).split(","));
                contactList.add(Menu);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
}




