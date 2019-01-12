package ivagonz.antroma.guinet.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ivagonz.antroma.guinet.R;

public class NavigationDrawerActivity extends AppCompatActivity {

    private Context context;
    private Intent intent;
    private DrawerLayout drawerLayout;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    public void setToolbar(Toolbar toolbar, String title) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }


    public void setupDrawerContent(final NavigationView navigationView, final DrawerLayout drawerLayout) {
        navigationView.requestFocus();
        //navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.nav_users:
                                intent = new Intent(context, UsersActivity.class);
                                position = 0;
                                break;
                            case R.id.nav_add:
                                intent = new Intent(context, AddUserActivity.class);
                                position = 1;
                                break;


                        }
                        if (intent != null) {
                            intent.putExtra("position", position);
                            finish();
                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                        } else if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                            drawerLayout.closeDrawer(GravityCompat.START);
                        }


                        return true;
                    }


                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*switch (id) {
            case R.id.acerca_de:
                Intent acerca = new Intent(NavigationDrawerActivity.this, AboutUsActivity.class);
                startActivity(acerca);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;


        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (position != 0)
                super.onBackPressed();
        }
    }

    public boolean networkStatus() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (netInfo != null && netInfo.isConnectedOrConnecting() || wifi != null && wifi.isWifiEnabled()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


}
