package com.afrozaar.wp_api_v2_client_android.data.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.afrozaar.wp_api_v2_client_android.data.WordPressContract;
import com.afrozaar.wp_api_v2_client_android.model.Type;

/**
 *
 * Created by Zain on 11/27/2017.
 */

public class TypeRepository extends BaseWpRepository implements WordPressContract.TypeColumns {

    public static final String TABLE_NAME = "types";

    public static final String SCHEMA = "CREATE TABLE " + TABLE_NAME + " ("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + BLOG_ID + " INTEGER " + WordPressContract.References.BLOG_ID + ","
            + NAME + " TEXT NOT NULL,"
            + SLUG + " TEXT,"
            + DESCRIPTION + " TEXT,"
            + LINK + " TEXT)";

    public static final int IDX_BLOG_ID = 1;
    public static final int IDX_NAME = 2;
    public static final int IDX_SLUG = 3;
    public static final int IDX_DESCRIPTION = 4;
    public static final int IDX_LINK = 5;

    public static ContentValues getContainsMap(Type type, long blogId) {
        ContentValues values = new ContentValues();

        values.put(BLOG_ID, blogId);

        return values;
    }

    public static ContentValues mapToContentValues(Type type, long blogId) {
        ContentValues values = new ContentValues();

        values.put(BLOG_ID, blogId);

        addValue(values, NAME, type.getName());
        addValue(values, SLUG, type.getSlug());
        addValue(values, DESCRIPTION, type.getDescription());
        addValue(values, LINK, type.getLink());

        return values;
    }

    public static Type mapFromCursor(Cursor cursor) {
        Type type = new Type();

        type.rowId = getRowId(cursor);

        type.withName(cursor.getString(IDX_NAME))
                .withSlug(cursor.getString(IDX_SLUG))
                .withDescription(cursor.getString(IDX_DESCRIPTION))
                .withLink(cursor.getString(IDX_LINK));

        return type;
    }
}
