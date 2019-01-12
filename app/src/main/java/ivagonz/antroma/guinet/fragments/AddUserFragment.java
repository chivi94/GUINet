package ivagonz.antroma.guinet.fragments;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import ivagonz.antroma.guinet.R;
import ivagonz.antroma.guinet.adapters.UserAdapter;
import ivagonz.antroma.guinet.asynctask.AddUserAsyncTask;
import ivagonz.antroma.guinet.database.UserContract;
import ivagonz.antroma.guinet.model.User;


public class AddUserFragment extends Fragment implements View.OnClickListener {


    private User user;
    private ArrayList<User> users;
    private ListView listView;
    private UserAdapter adapter;

    private SimpleCursorAdapter mAdapter;
    private static final String[] FROM = {UserContract.Column.NAME, UserContract.Column.SURNAME, UserContract.Column.ALIAS};
    private static final int[] TO = {R.id.users_tv_name, R.id.users_tv_surname, R.id.users_tv_alias};
    private static final int LOADER_ID = 42;

    private EditText et_alias, et_nombre, et_apellido, et_dni, et_email;
    private Button btn_addUser;

    public AddUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_fragment_add_user, container, false);
        et_alias = v.findViewById(R.id.et_add_alias);
        et_nombre = v.findViewById(R.id.et_add_nombre);
        et_apellido = v.findViewById(R.id.et_add_apellido);
        et_dni = v.findViewById(R.id.et_add_dni);
        et_email = v.findViewById(R.id.et_add_email);
        btn_addUser = v.findViewById(R.id.btn_addUser);

        btn_addUser.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_addUser:
                String user[] = new String[]{
                        et_alias.getText().toString(),
                        et_nombre.getText().toString(),
                        et_apellido.getText().toString(),
                        et_dni.getText().toString(),
                        et_email.getText().toString()
                };
                AddUserAsyncTask addUserAsyncTask = new AddUserAsyncTask(getActivity(),this);
                addUserAsyncTask.execute(user);
                break;
        }
    }


}
