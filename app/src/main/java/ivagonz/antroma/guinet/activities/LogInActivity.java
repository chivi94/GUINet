package ivagonz.antroma.guinet.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ivagonz.antroma.guinet.cons_and_preferences.*;
import ivagonz.antroma.guinet.R;

public class LogInActivity extends Activity implements View.OnClickListener {

    private Button btn_login;
    private EditText et_userName, et_password;

    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_login_activity);

        btn_login = findViewById(R.id.btn_login);
        et_userName = findViewById(R.id.et_login_username);
        et_password = findViewById(R.id.et_login_password);

        btn_login.setOnClickListener(this);
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
                    else
                        startActivity(new Intent(LogInActivity.this, UsersActivity.class).putExtra("position", 1));
                } else {
                    et_userName.requestFocus();
                    Toast.makeText(LogInActivity.this, getString(R.string.toast_notOk), Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
