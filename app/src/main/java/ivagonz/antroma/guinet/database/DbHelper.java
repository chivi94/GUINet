package ivagonz.antroma.guinet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String TAG = DbHelper.class.getSimpleName();

    // Constructor
    public DbHelper(Context context) {
        super(context, UserContract.DB_NAME, null, UserContract.DB_VERSION);
    }

    // Llamado para crear la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s (%s text primary key, %s text, %s text, %s text, %s text, %s text, %s text, %s text)",
                UserContract.TABLE,
                UserContract.Column.ALIAS,
                UserContract.Column.NAME,
                UserContract.Column.SURNAME,
                UserContract.Column.DNI,
                UserContract.Column.POSITION,
                UserContract.Column.PHONE,
                UserContract.Column.EMAIL,
                UserContract.Column.DEGREE);
        db.execSQL(sql);
    }

    // Llamado siempre que tengamos una nueva version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserContract.TABLE);
        onCreate(db);
    }
}
