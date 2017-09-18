package com.google.developer.bugmaster.data;

import android.provider.BaseColumns;

public final class BugsDbContract {

    public static final class bugsEntry implements BaseColumns {

        public static final String TABLE_NAME = "bugs";
        public static final String COLUMN_NAME_FRIENDLY_NAME = "friendly_name";
        public static final String COLUMN_NAME_SCIENTIFIC_NAME = "scientific_name";
        public static final String COLUMN_NAME_CLASSIFICATION = "classification";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_DANGER_LEVEL = "danger_level";
        public static final String SORT_BY_ASC = " ASC";
        public static final String SORT_BY_DESC = " DESC";

    }

}
