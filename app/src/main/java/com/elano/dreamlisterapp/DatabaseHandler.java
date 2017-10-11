package com.elano.dreamlisterapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Janeth on 10/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "WishListItemsManager.db";
    private static final String TABLE_WISH_LIST_ITEMS = "wish_lists_items";
    private static final String KEY_ID = "id";
    private static final String KEY_PHOTO = "photo";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";
    private long newRowId;
    private SQLiteDatabase mDatabase;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WISH_LIST_ITEMS_TABLE = "CREATE TABLE " + TABLE_WISH_LIST_ITEMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_PHOTO + " BLOB," + KEY_NAME + " TEXT, " + KEY_DESCRIPTION + " TEXT, "
                + KEY_PRICE + " REAL" + ")";
        db.execSQL(CREATE_WISH_LIST_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISH_LIST_ITEMS);
        onCreate(db);
    }

    public void addWishListItem(Item item) {
        mDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PHOTO, item.getImage());
        values.put(KEY_NAME, item.getName());
        values.put(KEY_DESCRIPTION, item.getDescription());
        values.put(KEY_PRICE, item.getPrice());

        newRowId = mDatabase.insert(TABLE_WISH_LIST_ITEMS, null, values);
    }

    public List<Item> getAllItems() {
        // getting all item
        List<Item> itemList = new ArrayList<>();
        // select all query
        String selectQuery = "SELECT * FROM " + TABLE_WISH_LIST_ITEMS;

        mDatabase = this.getWritableDatabase();
        Cursor cursor = mDatabase.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Item item = new Item();
                newRowId = cursor.getLong(0);
                item.setId(newRowId);
                item.setImage(cursor.getBlob(1));
                item.setName(cursor.getString(2));
                item.setDescription(cursor.getString(3));
                item.setPrice(cursor.getDouble(4));
                // adding item to list
                itemList.add(item);
                cursor.moveToNext();
            }
        }
        // return item list
        return itemList;
    }

    public void deleteWishListItem(Item item) {
        mDatabase = this.getWritableDatabase();
        mDatabase.delete(TABLE_WISH_LIST_ITEMS, KEY_ID + " = ?", new String[] { String.valueOf(item.getId()) });
        mDatabase.close();
    }
}
