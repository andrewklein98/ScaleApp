package com.example.scaleapp;
import android.database.sqlite.*;
import android.provider.BaseColumns;

//a lot of this copied from  https://developer.android.com/training/data-storage/sqlite

public final class ModeDbContract {
    private ModeDbContract(){};

    public static class ModeDbEntry implements BaseColumns{
        public static final String TABLE_NAME ="TONALITY";
        public static final String COLUMN_NAME_TITLE ="MODE";

    }
}
