package com.google.developer.bugmaster.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import com.google.developer.bugmaster.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class BugsDbHelper extends SQLiteOpenHelper {

    private static final String TAG = BugsDbHelper.class.getSimpleName();
    private List<Insect> insects;

    private static final String DATABASE_NAME = "insects_db";
    private static final int DATABASE_VERSION = 1;

    private Resources mResources;

    public BugsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mResources = context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: Create and fill the database

        db.execSQL("CREATE TABLE "
                + BugsDbContract.bugsEntry.TABLE_NAME + " ("
                + BugsDbContract.bugsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BugsDbContract.bugsEntry.COLUMN_NAME_FRIENDLY_NAME + " TEXT, "
                + BugsDbContract.bugsEntry.COLUMN_NAME_SCIENTIFIC_NAME + " TEXT, "
                + BugsDbContract.bugsEntry.COLUMN_NAME_CLASSIFICATION + " TEXT, "
                + BugsDbContract.bugsEntry.COLUMN_NAME_IMAGE + " TEXT, "
                + BugsDbContract.bugsEntry.COLUMN_NAME_DANGER_LEVEL + " INTEGER "
                + ");");

        try {

            readInsectsFromResources(db);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO: Handle database version upgrades
        int f = 0;
    }

    private void readInsectsFromResources(SQLiteDatabase db) throws IOException, JSONException {

        StringBuilder builder = new StringBuilder();
        InputStream in = mResources.openRawResource(R.raw.insects);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        in.close();
        reader.close();

        //Parse resource into key/values
        final String rawJson = builder.toString();
        //TODO: Parse JSON data and insert into the provided database instance
        new LoadBugsFromTask().execute(rawJson);

    }

    private class LoadBugsFromTask extends AsyncTask<String,Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            JSONArray jArray = null;
            JSONObject jObject = null;
            JSONObject currentObject;

            try {

                jObject = new JSONObject(params[0]);
                jArray = jObject.getJSONArray("insects");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            String friendlyName;
            String scientificName;
            String classification;
            String imageAsset;
            int dangerLevel;

            if(jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {

                    try {

                        currentObject = jArray.getJSONObject(i);

                        friendlyName = currentObject.getString("friendlyName");
                        scientificName = currentObject.getString("scientificName");
                        classification = currentObject.getString("classification");
                        imageAsset = currentObject.getString("imageAsset");
                        dangerLevel = currentObject.getInt("dangerLevel");

                        ContentValues cv = new ContentValues();

                        cv.put(BugsDbContract.bugsEntry.COLUMN_NAME_FRIENDLY_NAME, friendlyName);
                        cv.put(BugsDbContract.bugsEntry.COLUMN_NAME_SCIENTIFIC_NAME, scientificName);
                        cv.put(BugsDbContract.bugsEntry.COLUMN_NAME_CLASSIFICATION, classification);
                        cv.put(BugsDbContract.bugsEntry.COLUMN_NAME_IMAGE, imageAsset);
                        cv.put(BugsDbContract.bugsEntry.COLUMN_NAME_DANGER_LEVEL, dangerLevel);

                        SQLiteDatabase db = getWritableDatabase();

                        db.insert(BugsDbContract.bugsEntry.TABLE_NAME, null, cv);

                        db.close();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

}
