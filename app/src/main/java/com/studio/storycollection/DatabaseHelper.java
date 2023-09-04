package com.studio.storycollection;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StoryDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "stories";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_STORY = "story";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_STORY + " TEXT)";
        db.execSQL(CREATE_TABLE);
        initializeStories(db);  // Initialize stories when DB is created
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    // Call this method inside onCreate
    private void initializeStories(SQLiteDatabase db) {
        addStory(db, "Cinderella", "Once upon a time, there was a kind girl named Cinderella. She lived with her wicked stepmother and two stepsisters who treated her poorly. With the help of her Fairy Godmother, she went to the royal ball and captured the prince's heart. At midnight, she rushed home, leaving behind a glass slipper. The prince found her through the slipper, and they lived happily ever after.");
        addStory(db, "Snow White", "In a faraway kingdom, Snow White lived with her evil stepmother, the Queen. Jealous of Snow White's beauty, the Queen tried to get rid of her. Snow White took refuge with seven dwarfs. The Queen made three attempts on Snow White's life but failed each time. Finally, the prince came and kissed Snow White, breaking the Queen's spell. They lived happily ever after.");
        addStory(db, "Little Red Riding Hood", "There was a little girl known as Little Red Riding Hood because of her red-hooded cloak. One day, she went to visit her grandmother, taking a basket of treats. A wolf noticed her and reached her grandmother's house first, swallowing the old lady. When Little Red Riding Hood arrived, she found the wolf disguised as her grandmother. A passing huntsman heard them, ran in, and saved both Little Red Riding Hood and her grandmother by cutting open the wolf's belly. They all lived happily ever after.");
    }


    // This method adds a story to the database
    private void addStory(SQLiteDatabase db, String title, String story) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("story", story);
        db.insert("stories", null, contentValues);
    }
    // Insert a story
    public long addStory(String title, String story) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_STORY, story);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    // Fetch all stories
    @SuppressLint("Range")
    public ArrayList<String> getAllStories() {
        ArrayList<String> stories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                stories.add(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return stories;
    }

    // Delete a story by title
    public void deleteStory(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_TITLE + " = ?", new String[]{title});
    }

    // Fetch a specific story by title
    @SuppressLint("Range")
    public String getStory(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_STORY}, COLUMN_TITLE + " = ?",
                new String[]{title}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COLUMN_STORY));
        }

        return null;
    }
}
