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
import android.widget.ListView;

import java.util.ArrayList;

import ivagonz.antroma.guinet.R;
import ivagonz.antroma.guinet.adapters.UserAdapter;
import ivagonz.antroma.guinet.database.UserContract;
import ivagonz.antroma.guinet.model.User;


public class AddUserFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private User user;
    private ArrayList<User> users;
    private ListView listView;
    private UserAdapter adapter;

    private SimpleCursorAdapter mAdapter;
    private static final String[] FROM = {UserContract.Column.NAME, UserContract.Column.SURNAME, UserContract.Column.ALIAS};
    private static final int[] TO = {R.id.users_tv_name, R.id.users_tv_surname, R.id.users_tv_alias};
    private static final int LOADER_ID = 42;


    public AddUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.ly_fragment_add_user, container, false);


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
