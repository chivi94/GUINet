package ivagonz.antroma.guinet.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import ivagonz.antroma.elespinar.R;
import ivagonz.antroma.elespinar.cons_and_preferences.Constants;

public class NavigationDrawerActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
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
        navigationView.getMenu().getItem(4).setChecked(true);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        int id = menuItem.getItemId();
                        int position = 0;
                        switch (id) {
                            case R.id.nav_atp:
                                intent = new Intent(context, ATPChallengerActivity.class);
                                position = 1;
                                break;
                            case R.id.nav_fem:
                                intent = new Intent(context, FemITFActivity.class);
                                position = 2;
                                break;
                            case R.id.nav_tournament:
                                intent = new Intent(context, TournamentActivity.class);
                                position = 3;
                                break;
                            case R.id.nav_tickets:
                                intent = new Intent(context, TicketsActivity
                                        .class);
                                position = 4;
                                break;
                            case R.id.nav_news:
                                intent = new Intent(context, NewsActivity.class);
                                position = 0;
                                break;
                            case R.id.nav_activities:
                                intent = new Intent(context, ActivitiesActivity
                                        .class);
                                position = 5;
                                break;
                            case R.id.nav_history:
                                intent = new Intent(context, HistoryActivity.class);
                                position = 6;
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
        switch (id) {
            case R.id.acerca_de:
                Intent acerca = new Intent(NavigationDrawerActivity.this, AboutUsActivity.class);
                startActivity(acerca);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent app = null;
        switch (id) {
            case R.id.footer_facebook:
                app = getSocialIntent(this, Constants.getFbPackage(),Constants.getFbAppUri(),Constants.getFbWebUri());
                break;
            case R.id.footer_instagram:
                app = getSocialIntent(this, Constants.getIgPackage(),Constants.getIgAppUri(),Constants.getIgWebUri());
                break;
            case R.id.footer_twitter:
                app = getSocialIntent(this, Constants.getTwPackage(),Constants.getTwAppUri(),Constants.getTwWebUri());
                break;
            case R.id.footer_youtube:
                app = getSocialIntent(this, Constants.getYtPackage(),Constants.getYtAppUri(),Constants.getYtWebUri());
                break;
        }
        if (app != null) {
            startActivity(app);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
    }

    private Intent getSocialIntent(Context context, String pack, String appUri, String webUri){
        try {
            context.getPackageManager()
                    .getPackageInfo(pack, 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(appUri)); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(webUri)); //catches and opens a url to the desired page
        }
    }

    public boolean networkStatus() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
            if (netInfo != null && netInfo.isConnectedOrConnecting() || wifi !=null && wifi.isWifiEnabled()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }



}
