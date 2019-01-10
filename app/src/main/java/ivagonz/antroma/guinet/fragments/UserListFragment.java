package ivagonz.antroma.guinet.fragments;


import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ivagonz.antroma.guinet.R;
import ivagonz.antroma.guinet.adapters.UserAdapter;
import ivagonz.antroma.guinet.database.RefreshService;
import ivagonz.antroma.guinet.database.UserContract;
import ivagonz.antroma.guinet.model.User;


public class UserListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private User user;
    private ArrayList<User> users;
    private ListView listView;
    private UserAdapter adapter;

    private SimpleCursorAdapter mAdapter;
    private static final String[] FROM = {UserContract.Column.NAME};
    private static final int[] TO = {R.id.users_tv_name};
    private static final int LOADER_ID = 42;

    public UserListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_fragment_userlist, container, false);
        listView = v.findViewById(R.id.lv_userFragment_users);
        getActivity().startService(new Intent(getActivity().getApplicationContext(), RefreshService.class));
        users = new ArrayList();
        users.add(new User("ivan", "gonzalez", "chivi91"));
        users.add(new User("antonio", "roman lopez", "antrom"));
        adapter = new UserAdapter(getActivity().getApplicationContext(), users);
        mAdapter = new SimpleCursorAdapter(getActivity(), R.layout.ly_users_listview, null, FROM, TO, 0);
        listView.setAdapter(mAdapter);
        getLoaderManager().initLoader(LOADER_ID, null, this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user = (User) adapter.getItem(position);
                Toast.makeText(UserListFragment.this.getContext(), user.getNombre(), Toast.LENGTH_SHORT).show();
                /**TODO cargar el siguiente activity con la informacion del usuario recogido
                 * */
            }
        });

        return v;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id != LOADER_ID)
            return null;
        return new CursorLoader(getActivity(), UserContract.CONTENT_URI, null, null,
                null, UserContract.DEFAULT_SORT);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

}
