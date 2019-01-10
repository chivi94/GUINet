package ivagonz.antroma.guinet.database;

import android.app.IntentService;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;

import ivagonz.antroma.guinet.model.User;

public class RefreshService extends IntentService {

    static final String TAG = "RefreshService";

    private boolean runFlag = false;
    private DbHelper dbHelper;
    private SQLiteDatabase db;

    public RefreshService() {
        super(TAG);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DbHelper(this);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //El delay se mete para hacer actualizaciones graduales. Nosotros queremos actualizar cuando haya cambios. Tenerlo en cuenta

        this.runFlag = true;
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        while (runFlag) {
            //En este apartado hay que hacer la conexión con el api rest
            List<User> users = new ArrayList<>();
            users.add(new User(1,"ivan", "gonzalez", "chivi91"));
            users.add(new User(2,"antonio", "roman lopez", "antrom"));
            // Iteramos sobre todos los componentes de timeline
            //db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            for (User user : users) {
                values.clear();
                values.put(UserContract.Column.ID, user.getId());
                values.put(UserContract.Column.ALIAS, user.getAlias());
                values.put(UserContract.Column.NAME, user.getNombre());
                values.put(UserContract.Column.SURNAME, user.getApellido());
                values.put(UserContract.Column.DNI, user.getDni());
                values.put(UserContract.Column.POSITION, user.getCargo());
                values.put(UserContract.Column.PHONE, user.getTlf());
                values.put(UserContract.Column.EMAIL, user.getEmail());
                values.put(UserContract.Column.DEGREE, user.getCarrera());
                /*db.insertWithOnConflict(UserContract.TABLE, null, values,
                        SQLiteDatabase.CONFLICT_IGNORE);*/
                Uri uri = getContentResolver().insert(UserContract.CONTENT_URI, values);
            }
            // Cerrar la base de datos
            //db.close();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.runFlag = false;
    }
}
