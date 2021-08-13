package robin.lai.c346.rp20019018.shopping.list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shoppingList.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ITEM = "item";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_STARS = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTableSQL ="CREATE TABLE " + TABLE_ITEM +"("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_CATEGORY + " TEXT,"
                + COLUMN_STARS + " FLOAT)";
        db.execSQL(createTableSQL);
        Log.i("info","created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        // Create table(s) again
        onCreate(db);

    }

    public long insertItem(String name, String description, String category, float stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_DESCRIPTION,description);
        values.put(COLUMN_CATEGORY,category);
        values.put(COLUMN_STARS,stars);
        long result = db.insert(TABLE_ITEM, null, values);
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        }
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Item> getAllItem() {
        ArrayList<Item> items = new ArrayList<Item>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME +  "," + COLUMN_DESCRIPTION + "," + COLUMN_CATEGORY + "," + COLUMN_STARS + " FROM " + TABLE_ITEM
                + " ORDER BY " + COLUMN_NAME + " ASC ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String itemName = cursor.getString(1);
                String itemDesc = cursor.getString(2);
                String itemCat = cursor.getString(3);
                float itemStar = cursor.getFloat(4);
                Item item = new Item(id,itemName,itemDesc,itemCat,itemStar);
                items.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return items;
    }

    public int updateItem(Item data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,data.getName());
        values.put(COLUMN_DESCRIPTION,data.getDescription());
        values.put(COLUMN_CATEGORY,data.getCategory());
        values.put(COLUMN_STARS,data.getRating());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_ITEM, values, condition, args);
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }
        db.close();
        return result;
    }

    public int deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_ITEM, condition, args);
        db.close();
        return result;
    }

}

