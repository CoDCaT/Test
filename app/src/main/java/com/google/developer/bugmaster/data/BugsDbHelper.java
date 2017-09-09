package com.google.developer.bugmaster.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.developer.bugmaster.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

/**
 * Database helper class to facilitate creating and updating
 * the database from the chosen schema.
 */
public class BugsDbHelper extends SQLiteOpenHelper {

    private static final String TAG = BugsDbHelper.class.getSimpleName();
    private List<Insect> insects;


    private static final String DATABASE_NAME = "insects.db";
    private static final int DATABASE_VERSION = 2;
    static final String TABLE_NAME = "bugs";

    //Used to read data from res/ and assets/
    private Resources mResources;

    public BugsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mResources = context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: Create and fill the database
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + "_id INTEGER PRIMARY KEY,"
                + "commonName TEXT"
                + "scientificName TEXT"
                + "classification TEXT"
                + "image TEXT"
                + "dangerLevel INTEGER"
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

    /**
     * Streams the JSON data from insect.json, parses it, and inserts it into the
     * provided {@link SQLiteDatabase}.
     *
     * @param db Database where objects should be inserted.
     * @throws IOException
     * @throws JSONException
     */
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

        //MyCod------------------------------------***
        JSONObject jObject = new JSONObject(rawJson);
        JSONArray jArray = jObject.getJSONArray("insects");
        String friendlyName = "";
        String scientificName = "";
        String classification = "";
        String imageAsset = "";
        int dangerLevel = 0;

        insects = new ArrayList<>();

        for (int i = 0; i < jArray.length(); i++) {
            friendlyName = jArray.getJSONObject(i).getString("friendlyName");
            scientificName = jArray.getJSONObject(i).getString("scientificName");
            classification = jArray.getJSONObject(i).getString("classification");
            imageAsset = jArray.getJSONObject(i).getString("imageAsset");
            dangerLevel = jArray.getJSONObject(i).getInt("dangerLevel");

            insects.add(new Insect(friendlyName, scientificName, classification, imageAsset, dangerLevel));

        }


        for (int i = 0; i < insects.size(); i++) {
            ContentValues cv = new ContentValues();
            cv.put("friendlyName", insects.get(i).getName());
            cv.put("scientificName", insects.get(i).getScientificName());
            cv.put("classification", insects.get(i).getClassification());
            cv.put("imageAsset", insects.get(i).getImageAsset());
            cv.put("dangerLevel", insects.get(i).getDangerLevel());

            db.insert(TABLE_NAME, null, cv);
        }
//        db.close();


        //MyCod-------------------------------------------***

    }

}
