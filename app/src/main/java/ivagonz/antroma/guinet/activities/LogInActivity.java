package ivagonz.antroma.guinet.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import ivagonz.antroma.guinet.asynctask.LoginAsyncTask;
import ivagonz.antroma.guinet.cons_and_preferences.*;
import ivagonz.antroma.guinet.R;

public class LogInActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Button btn_login;
    private EditText et_userName, et_password;
    private CheckBox ch_holdSession, ch_showPassword;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private LoginAsyncTask loginAsyncTask;

    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_login_activity);

        preferences = LogInActivity.this.getApplicationContext().getSharedPreferences(SharedPreferencesConstants.PREFERENCES_NAME, LogInActivity.this.MODE_PRIVATE);
        editor = preferences.edit();

        btn_login = findViewById(R.id.btn_login);
        et_userName = findViewById(R.id.et_login_username);
        et_password = findViewById(R.id.et_login_password);
        ch_holdSession = findViewById(R.id.ch_holdSession);
        ch_showPassword = findViewById(R.id.ch_showPassword);

        btn_login.setOnClickListener(this);
        ch_showPassword.setOnCheckedChangeListener(this);

        checkLogin();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login:
                //Hacemos trim para recortar espacios en blanco innecesarios
                username = et_userName.getText().toString().trim();
                password = et_password.getText().toString().trim();
                //Si ha introducido usuario y password, proceder a iniciar sesion.
                if (!"".equals(username) && username != null && !"".equals(password) && password != null) {
                    /* TODO Incluir comprobacion con la BD y el AsyncTask
                     * */
                    if (!Constants.getUsername().equals(username) || !Constants.getPassword().equals(password))
                        /*TODO Iniciar sesión y cargar la vista de listado de miembros
                         */
                        Toast.makeText(LogInActivity.this, getString(R.string.toast_loginNotOk), Toast.LENGTH_SHORT).show();
                    else {
                        if (ch_holdSession.isChecked()) {
                            if (!SharedPreferencesConstants.autoLogin) {
                                newLogin(username, password);
                            } else {
                                catchOldLogin();
                            }
                        } else {
                            normalLogin();
                        }
                        startActivity(new Intent(LogInActivity.this, UsersActivity.class).putExtra("position", 1));
                    }
                } else {
                    et_userName.requestFocus();
                    Toast.makeText(LogInActivity.this, getString(R.string.toast_notOk), Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    /**
     * Método que inicia una sesión nueva en la aplicación, y guarda los datos del usuario que está iniciando sesión.
     *
     * @param user Nombre de usuario que está iniciando sesión.
     * @param pass Contraseña de usuario que está iniciando sesión.
     */
    private void newLogin(String user, String pass) {
        //Editamos las preferences
        editor.putString(SharedPreferencesConstants.USERNAME, user);
        editor.putString(SharedPreferencesConstants.PASSWORD, pass);
        editor.putBoolean(SharedPreferencesConstants.HOLD_SESSION, true);
        editor.commit();
        loginAsyncTask = new LoginAsyncTask(LogInActivity.this, user, pass);
        loginAsyncTask.execute();
    }

    /**
     * Método que inicia sesión en el sistema si hay datos guardados en el terminal.
     */
    private void catchOldLogin() {
        String username = preferences.getString(SharedPreferencesConstants.USERNAME, null);
        String user_password = preferences.getString(SharedPreferencesConstants.PASSWORD, null);
        et_userName.setText(username);
        et_password.setText(user_password);
        loginAsyncTask = new LoginAsyncTask(LogInActivity.this, username, user_password);
        loginAsyncTask.execute();
    }

    /**
     * Método que inicia sesión en el sistema sin guardar ningún tipo de credencial.
     */
    private void normalLogin() {
        loginAsyncTask = new LoginAsyncTask(LogInActivity.this, et_userName.getText().toString(), et_password.getText().toString());
        loginAsyncTask.execute();
    }

    /**
     * Método que comprueba si hay datos guardados de una sesión antigua. En caso afirmativo, inicia sesión automáticamente.
     */
    private void checkLogin() {
        String username = preferences.getString(SharedPreferencesConstants.USERNAME, null);
        String user_password = preferences.getString(SharedPreferencesConstants.PASSWORD, null);
        boolean isHoldSession = preferences.getBoolean(SharedPreferencesConstants.HOLD_SESSION, false);

        if (username != null && user_password != null && isHoldSession) {
            SharedPreferencesConstants.autoLogin = true;
            ch_holdSession.setChecked(SharedPreferencesConstants.autoLogin);
            et_password.setText(user_password);
            et_userName.setText(username);
            //Hace click en el boton de forma automatica
            btn_login.performClick();
        }
    }
}
