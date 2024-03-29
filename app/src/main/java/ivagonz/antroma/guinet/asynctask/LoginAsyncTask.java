package ivagonz.antroma.guinet.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import ivagonz.antroma.guinet.R;

public class LoginAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private ProgressDialog progressDialog;
    private Context context;
    //private DatabaseConnect connect;
    private String sessionId;
    private String password;


    public LoginAsyncTask(Context context, String sessionId, String password) {
        this.context = context;
        this.sessionId = sessionId;
        this.password = password;
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
        //connect = new DatabaseConnect();
    }

    @Override
    protected void onPreExecute() {
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage(context.getString(R.string.progressdialog_iniciarSesion));
                progressDialog.show();
            }
        });
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        //eturn connect.connectToGeoTruck(sessionId, password, context);
        return true;
    }

    @Override
    protected void onCancelled() {
        Toast.makeText(context, context.getString(R.string.asyncTastk_cancelled), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Boolean loginSuccessful) {
        if (!loginSuccessful) {
            Toast.makeText(context, context.getResources().getString(R.string.toast_loginErroneo), Toast.LENGTH_SHORT).show();
        }
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public Context getContext() {
        return context;
    }
}
