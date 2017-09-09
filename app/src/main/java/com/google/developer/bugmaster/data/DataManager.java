package com.google.developer.bugmaster.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton that controls access to the SQLiteDatabase instance
 * for this application.
 */
public class DataManager {

    private static DataManager sInstance;
    private List<Insect> insects;

    public static synchronized DataManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataManager(context.getApplicationContext());
        }

        return sInstance;
    }

    private BugsDbHelper mBugsDbHelper;

    private DataManager(Context context) {
//        mBugsDbHelper = new BugsDbHelper(context);
//        mBugsDbHelper.getWritableDatabase();
    }

    /**
     * Return a {@link Cursor} that contains every insect in the database.
     *
     * @param sortOrder Optional sort order string for the query, can be null
     * @return {@link Cursor} containing all insect results.
     */
    public Cursor queryAllInsects(String sortOrder) {
        //TODO: Implement the query
        Cursor c = mBugsDbHelper.getWritableDatabase().query("bugs", null, null, null, null, null, sortOrder);
        return c;
    }

    /**
     * Return a {@link Cursor} that contains a single insect for the given unique id.
     *
     * @param id Unique identifier for the insect record.
     * @return {@link Cursor} containing the insect result.
     */
    public Cursor queryInsectsById(int id) {
        //TODO: Implement the query
        return null;
    }

    public List<Insect> getAllInsect(String sortOrder){

        SQLiteDatabase db = mBugsDbHelper.getWritableDatabase();
        Cursor c = db.query(BugsDbHelper.TABLE_NAME, null, null, null, null, null, sortOrder);

        if (c.moveToFirst()) {
            insects = new ArrayList<>();
            int friendlyName = c.getColumnIndex("friendlyName");
            int scientificName = c.getColumnIndex("scientificName");
            int classification = c.getColumnIndex("classification");
            int imageAsset = c.getColumnIndex("imageAsset");
            int dangerLevel = c.getColumnIndex("dangerLevel");

            do {

                insects.add(new Insect(c.getString(friendlyName), c.getString(scientificName), c.getString(classification), c.getString(imageAsset), c.getInt(dangerLevel)));

            } while (c.moveToNext());
        } else {

        }


//        insects.add(new Insect("12", "12", "12", "12", 0));

        return insects;
    }
}
