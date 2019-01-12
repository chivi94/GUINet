package ivagonz.antroma.guinet.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ivagonz.antroma.guinet.R;
import ivagonz.antroma.guinet.asynctask.AddUserAsyncTask;


public class AddUserFragment extends Fragment implements View.OnClickListener {


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


    public EditText getEt_alias() {
        return et_alias;
    }

    public EditText getEt_nombre() {
        return et_nombre;
    }

    public EditText getEt_apellido() {
        return et_apellido;
    }

    public EditText getEt_dni() {
        return et_dni;
    }

    public EditText getEt_email() {
        return et_email;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_addUser:
                String alias = et_alias.getText().toString();
                String nombre = et_nombre.getText().toString();
                String apellido = et_apellido.getText().toString();
                String dni = et_dni.getText().toString();
                String email = et_email.getText().toString();
                if (!"".equals(alias) && alias != null &&
                        !"".equals(nombre) && nombre != null &&
                        !"".equals(apellido) && apellido != null &&
                        !"".equals(dni) && dni != null &&
                        !"".equals(email) && email != null) {
                    String user[] = new String[]{
                            alias,
                            nombre,
                            apellido,
                            dni,
                            email
                    };
                    AddUserAsyncTask addUserAsyncTask = new AddUserAsyncTask(getActivity(), this);
                    addUserAsyncTask.execute(user);
                }else{
                    Toast.makeText(getActivity(),getActivity().getString(R.string.emptyField),Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
