package ivagonz.antroma.guinet.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import ivagonz.antroma.guinet.R;
import ivagonz.antroma.guinet.pageradapter.ViewPagerAdapter;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends NavigationDrawerActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Intent intent;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_news_activity);
        drawerLayout = (DrawerLayout) findViewById(R.id.news_activity_drawer_ly);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar, getResources().getString(R.string.news_name));
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(
                actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.base_naw_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView, drawerLayout);
        }
        intent = getIntent();
        position = intent.getIntExtra("position", 0);
        navigationView.getMenu().getItem(position).setChecked(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Bundle args = null;

        String[] names = new String[]{getResources().getString(R.string.news_tournament), getResources().getString(R.string.news_sport),
                getResources().getString(R.string.news_communication), getResources().getString(R.string.news_did_you_know)};
        String[] url = new String[]{ivagonz.antroma.elespinar.cons_and_preferences.Constants.getNewsTournamentUrl(), ivagonz.antroma.elespinar.cons_and_preferences.Constants.getNewsSportUrl(), ivagonz.antroma.elespinar.cons_and_preferences.Constants.getNewsCommunicationUrl(), ivagonz.antroma.elespinar.cons_and_preferences.Constants.getNewsDidYouKnowUrl()};
        for (int i = 0; i < names.length; i++) {
            args = new Bundle();
            args.putString("url", url[i]);
           // newsTournamentFragment = new ivagonz.antroma.elespinar.news.NewsFragment();
           // newsTournamentFragment.setArguments(args);
           // adapter.addFrag(newsTournamentFragment, names[i]);
        }
        viewPager.setAdapter(adapter);
    }
}
