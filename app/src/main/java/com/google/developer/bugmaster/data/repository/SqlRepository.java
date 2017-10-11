package com.google.developer.bugmaster.data.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.developer.bugmaster.Utils;
import com.google.developer.bugmaster.data.BugsDbContract;
import com.google.developer.bugmaster.data.BugsDbHelper;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.data.specification.SQLiteSpecification;
import com.google.developer.bugmaster.data.specification.Specification;

import java.util.ArrayList;
import java.util.List;


public class SqlRepository implements IRepository<Insect>{

    private static SqlRepository sInstance;
    private BugsDbHelper mBugsDbHelper;

    private SqlRepository(Context context) {
        mBugsDbHelper = new BugsDbHelper(context);
    }

    public static synchronized SqlRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SqlRepository(context);
        }

        return sInstance;
    }

    @Override
    public void add(Insect item) {

    }

    @Override
    public void remove(Insect item) {

    }

    @Override
    public void update(Insect item) {

    }

    @Override
    public List<Insect> query(Specification specification) {

        final SQLiteSpecification sqlSpecification = (SQLiteSpecification) specification;

        SQLiteDatabase db = mBugsDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery(sqlSpecification.toSqlQuery(), new String[]{});

        ArrayList<Insect> insects = new ArrayList<>();

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
    public String readSharedSetting(Context ctx, String settingName, String defaultValue) {

        return Utils.readSharedSetting(ctx, settingName, defaultValue);
    }

    @Override
    public void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        Utils.saveSharedSetting(ctx, settingName, settingValue);
    }
}
