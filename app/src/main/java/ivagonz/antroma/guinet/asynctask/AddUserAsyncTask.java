package ivagonz.antroma.guinet.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ivagonz.antroma.guinet.R;
import ivagonz.antroma.guinet.activities.AddUserActivity;
import ivagonz.antroma.guinet.fragments.AddUserFragment;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddUserAsyncTask extends AsyncTask<String, Void, Integer> {

    private Context context;
    private Fragment fragment;
    private ProgressDialog progressDialog;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public AddUserAsyncTask(Context context, Fragment fragment) {
        this.context = context;
        this.fragment = fragment;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {

        ((AddUserActivity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.setMessage(context.getString(R.string.addingUser));
                progressDialog.show();
            }
        });
    }

    // Llamada al empezar
    @Override
    protected Integer doInBackground(String... params) {
        OkHttpClient client;
        Request request;
        Response response = null;
        JSONObject jsonObject = new JSONObject();

        try {
            client = new OkHttpClient();
            jsonObject.accumulate("alias", params[0]);
            jsonObject.accumulate("name", params[1]);
            jsonObject.accumulate("lastname", params[2]);
            jsonObject.accumulate("dni", params[3]);
            jsonObject.accumulate("email", params[4]);
            RequestBody body = RequestBody.create(JSON, jsonObject.toString());
            request = new Request.Builder()
                    .url("https://gui.uva.es/guinetv3/members/")
                    .post(body)
                    .build();

            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response.code();
    }

    // Llamada cuando la actividad en background ha terminado
    @Override
    protected void onPostExecute(Integer result) {
        // Acción al completar la actualización del estado
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (result == 201) {
            //Todo ok
            Toast.makeText(context,context.getString(R.string.userCreated),Toast.LENGTH_SHORT).show();
            ((AddUserFragment)fragment).getEt_alias().setText("");
            ((AddUserFragment)fragment).getEt_alias().requestFocus();
            ((AddUserFragment)fragment).getEt_nombre().setText("");
            ((AddUserFragment)fragment).getEt_apellido().setText("");
            ((AddUserFragment)fragment).getEt_dni().setText("");
            ((AddUserFragment)fragment).getEt_email().setText("");

        }else if(result == 409){
            //Algo ha ido mal
            Toast.makeText(context,context.getString(R.string.alreadyExist),Toast.LENGTH_SHORT).show();
            /*StatusActivity statusActivity = ((StatusActivity) context);
            final Snackbar snackbar = Snackbar.make(statusActivity.findViewById(R.id.btn_tweet), result, Snackbar.LENGTH_LONG);
            snackbar.setActionTextColor(statusActivity.getResources().getColor(R.color.snackbarAction));
            snackbar.setAction(statusActivity.getResources().getString(R.string.snackbar_okey), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();*/
        }else{
            Toast.makeText(context,context.getString(R.string.internet),Toast.LENGTH_SHORT).show();
        }

    }


}
