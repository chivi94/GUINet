package ivagonz.antroma.guinet.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import ivagonz.antroma.guinet.model.User;

public class UserProvider extends ContentProvider {
    private static final String TAG = UserProvider.class.getSimpleName();
    private DbHelper dbHelper;
    private static final UriMatcher sURIMatcher;

    static {
        sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sURIMatcher.addURI(UserContract.AUTHORITY, UserContract.TABLE,
                UserContract.STATUS_DIR);
        sURIMatcher.addURI(UserContract.AUTHORITY, UserContract.TABLE + "/#",
                UserContract.STATUS_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri ret = null;
// Nos aseguramos de que la URI es correcta
        if (sURIMatcher.match(uri) != UserContract.STATUS_DIR) {
            throw new IllegalArgumentException("uri incorrecta: " + uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long rowId = db.insertWithOnConflict(UserContract.TABLE, null,
                contentValues, SQLiteDatabase.CONFLICT_IGNORE);
// ¿Se insertó correctamente?
        if (rowId != -1) {
            long id = contentValues.getAsLong(UserContract.Column.ID);
            ret = ContentUris.withAppendedId(uri, id);
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return ret;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s,
                      @Nullable String[] strings) {
        String where;
        switch (sURIMatcher.match(uri)) {
            case UserContract.STATUS_DIR:
                where = s;
                break;
            case UserContract.STATUS_ITEM:
                long id = ContentUris.parseId(uri);
                where = UserContract.Column.ID
                        + "="
                        + id
                        + (TextUtils.isEmpty(s) ? "" : " and ( " + s + " )");
                break;
            default:
                throw new IllegalArgumentException("uri incorrecta: " + uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret = db.update(UserContract.TABLE, contentValues, where, strings);
        if (ret > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return ret;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        String where;
        switch (sURIMatcher.match(uri)) {
            case UserContract.STATUS_DIR:
                where = s;
                break;
            case UserContract.STATUS_ITEM:
                long id = ContentUris.parseId(uri);
                where = UserContract.Column.ID
                        + "="
                        + id
                        + (TextUtils.isEmpty(s) ? "" : " and ( " + s + " )");
                break;
            default:
                throw new IllegalArgumentException("uri incorrecta: " + uri);
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int ret = db.delete(UserContract.TABLE, where, strings);
        if (ret > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return ret;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s,
                        @Nullable String[] strings1, @Nullable String s1) {
        String where;
        switch (sURIMatcher.match(uri)) {
            case UserContract.STATUS_DIR:
                where = s;
                break;
            case UserContract.STATUS_ITEM:
                long id = ContentUris.parseId(uri);
                where = UserContract.Column.ID
                        + "="
                        + id
                        + (TextUtils.isEmpty(s) ? "" : " and ( " + s + " )");
                break;
            default:
                throw new IllegalArgumentException("uri incorrecta: " + uri);
        }
        String orderBy = (TextUtils.isEmpty(s1)) ? UserContract.DEFAULT_SORT : s1;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(UserContract.TABLE, strings, where, strings1, null, null,
                orderBy);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sURIMatcher.match(uri)) {
            case UserContract.STATUS_DIR:
                return "vnd.android.cursor.dir/vnd.ivagonz.antroma.guinet.provider.status";
            case UserContract.STATUS_ITEM:
                return
                        "vnd.android.cursor.item/vnd.ivagonz.antroma.guinet.provider.status";
            default:
                throw new IllegalArgumentException("uri incorrecta: " + uri);
        }
    }

}