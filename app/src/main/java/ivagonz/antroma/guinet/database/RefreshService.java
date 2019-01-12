package ivagonz.antroma.guinet.database;

import android.app.IntentService;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
            List<User> users = getUsers();
            Log.d("Usuario recogido:",users.get(0).getName());
            // Iteramos sobre todos los componentes de timeline
            //db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            for (User user : users) {
                values.clear();
                /*values.put(UserContract.Column.ID, user.getId());
                values.put(UserContract.Column.ALIAS, user.getAlias());
                values.put(UserContract.Column.NAME, user.getName());
                values.put(UserContract.Column.SURNAME, user.getLastname());
                values.put(UserContract.Column.DNI, user.getDni());
                //values.put(UserContract.Column.POSITION, user.getCargo());
                //values.put(UserContract.Column.PHONE, user.getPhone());
                values.put(UserContract.Column.EMAIL, user.getEmail());
                //values.put(UserContract.Column.DEGREE, user.getGrade());
                Uri uri = getContentResolver().insert(UserContract.CONTENT_URI, values);*/
            }
            this.runFlag = false;
        }
        this.stopSelf();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.runFlag = false;
    }

    private List<User> getUsers() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://gui.uva.es/guinetv3/members").build();
        Response response = null;
        List<User> users = null;

        try {
            response = client.newCall(request).execute();
            String jsonData = response.body().string();
            JSONArray jsonArray = new JSONArray(jsonData);
            users = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                User user = new User(
                        i,
                        jsonObject.getString("name"),
                        jsonObject.getString("lastname"),
                        jsonObject.getString("alias"),
                        jsonObject.getString("dni"),
                        jsonObject.getString("email")

                );
                Log.d("Usuario:", jsonObject.get("name").toString());
                users.add(user);
            }
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
