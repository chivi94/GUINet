package ivagonz.antroma.guinet.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import ivagonz.antroma.guinet.R;
import ivagonz.antroma.guinet.activities.LogInActivity;
import ivagonz.antroma.guinet.activities.UsersActivity;
import ivagonz.antroma.guinet.cons_and_preferences.SharedPreferencesConstants;
import ivagonz.antroma.guinet.model.User;

public class LogoutAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private ProgressDialog progressDialog;
    private Context context;
    private String usuario;


    public LogoutAsyncTask(Context context) {
        this.context = context;
        //this.usuario = usuario;
        //Customización del diálogo de carga
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(true);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, context.getString(R.string.progressdialog_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancel(true);
                progressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onPreExecute() {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage(context.getString(R.string.progressdialog_logout));
                progressDialog.show();
            }
        });
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        //return connect.logOut(usuario);
        return true;
    }

    @Override
    protected void onCancelled() {
        Toast.makeText(context, context.getString(R.string.asyncTastk_cancelled), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Boolean logoutSuccessful) {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (logoutSuccessful) {
            logOutMe();
        } else {
            Toast.makeText(context, R.string.toast_error, Toast.LENGTH_SHORT).show();
        }
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public Context getContext() {
        return context;
    }

    private void logOutMe() {
        SharedPreferences preferences = context.getSharedPreferences(SharedPreferencesConstants.PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String user = preferences.getString(SharedPreferencesConstants.USERNAME, null);
        String password = preferences.getString(SharedPreferencesConstants.PASSWORD, null);
        boolean holdSession = preferences.getBoolean(SharedPreferencesConstants.HOLD_SESSION, false);
        if (user != null && password != null && holdSession) {
            //Elimino los valores de las SharedPreferences
            editor.remove(SharedPreferencesConstants.USERNAME);
            editor.remove(SharedPreferencesConstants.PASSWORD);
            editor.remove(SharedPreferencesConstants.HOLD_SESSION);
            editor.commit();
            //Digo que no se autologee porque ha cerrado sesión
            SharedPreferencesConstants.autoLogin = false;
        }
        //Vuelvo al main
        ((UsersActivity) context).finish();
        Intent mainActivity = new Intent(context, LogInActivity.class);
        context.startActivity(mainActivity);
    }
}
