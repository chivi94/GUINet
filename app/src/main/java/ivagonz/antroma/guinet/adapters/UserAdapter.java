package ivagonz.antroma.guinet.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import ivagonz.antroma.guinet.R;
import ivagonz.antroma.guinet.model.User;

public class UserAdapter extends ArrayAdapter {

    private Context context;
    private List<User> users;

    public UserAdapter(@NonNull Context context, List users) {
        super(context, R.layout.ly_users_listview, users);
        this.context = context;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.ly_users_listview, null);

        TextView name = item.findViewById(R.id.users_tv_name);
        name.setText(users.get(position).getNombre());

        TextView surname = item.findViewById(R.id.users_tv_surname);
        surname.setText(users.get(position).getApellido());

        TextView alias = item.findViewById(R.id.users_tv_alias);
        alias.setText(users.get(position).getAlias());

        return item;
    }

}
