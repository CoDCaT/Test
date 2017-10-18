package com.google.developer.bugmaster.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager sInstance;
    private List<Insect> insects;

    private BugsDbHelper mBugsDbHelper;

    public static synchronized DataManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataManager(context.getApplicationContext());
        }

        return sInstance;
    }

    private DataManager(Context context) {
        mBugsDbHelper = new BugsDbHelper(context);
    }

    public List<Insect> getAllInsect(String sortOrder){

        //TODO: refactor by not main thread and select fields us "String[]"

        SQLiteDatabase db = mBugsDbHelper.getReadableDatabase();
        Cursor c = db.query(BugsDbContract.bugsEntry.TABLE_NAME, null, null, null, null, null, sortOrder);

        insects = new ArrayList<>();

        if (c.moveToFirst()) {

            int friendlyName = c.getColumnIndex(BugsDbContract.bugsEntry.COLUMN_NAME_FRIENDLY_NAME);
            int scientificName = c.getColumnIndex(BugsDbContract.bugsEntry.COLUMN_NAME_SCIENTIFIC_NAME);
            int classification = c.getColumnIndex(BugsDbContract.bugsEntry.COLUMN_NAME_CLASSIFICATION);
            int imageAsset = c.getColumnIndex(BugsDbContract.bugsEntry.COLUMN_NAME_IMAGE);
            int dangerLevel = c.getColumnIndex(BugsDbContract.bugsEntry.COLUMN_NAME_DANGER_LEVEL);

            do {

                insects.add(new Insect(c.getString(friendlyName), c.getString(scientificName), c.getString(classification), c.getString(imageAsset), c.getInt(dangerLevel)));

            } while (c.moveToNext());
        } else {

        }

        return insects;
    }

}
