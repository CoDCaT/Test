package com.google.developer.bugmaster.data.repository;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.developer.bugmaster.data.BugsDbContract;
import com.google.developer.bugmaster.data.BugsDbHelper;
import com.google.developer.bugmaster.data.Insect;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.content.Context.MODE_PRIVATE;

@Singleton
public class AppRepository implements IRepository{

    private String PREFERENCES_FILE = "PREFERENCES_FILE";

    private List<Insect> insects;
    private SharedPreferences preferences;
    BugsDbHelper mBugsDbHelper;

    @Inject
    public AppRepository(Context context, BugsDbHelper mBugsDbHelper) {
//        mBugsDbHelper = new BugsDbHelper(context);
        this.mBugsDbHelper = mBugsDbHelper;
        preferences = context.getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE);
    }

    public List<Insect> getAllInsect(){

        //TODO: refactor by not main thread and select fields us "String[]"

        SQLiteDatabase db = mBugsDbHelper.getReadableDatabase();
        Cursor c = db.query(BugsDbContract.bugsEntry.TABLE_NAME, null, null, null, null, null, null);

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

    @Override
    public List<Insect> getCurrentInsect() {
        return insects;
    }

    @Override
    public String readFromPreference(String key) {
        return preferences.getString(key, "name");
    }

    @Override
    public void saveToPreference(String value) {
        preferences.edit().putString("sort", value).apply();
    }


}
