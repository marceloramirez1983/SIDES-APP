package android.marcelo_ramirez.sides;

import android.content.Intent;
import android.marcelo_ramirez.sides.adapter.ViewPagerAdapter;
import android.marcelo_ramirez.sides.fragment.MeritFragment;
import android.marcelo_ramirez.sides.fragment.ProfileFragment;
import android.marcelo_ramirez.sides.fragment.SanctionFragment;
import android.marcelo_ramirez.sides.model.SanctionDB;
import android.marcelo_ramirez.sides.service.GetAllFoulByGroupAsync;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    /*boolean viewIsAtHome;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getStringExtra("id_ci") == null) {
            startActivity(new Intent(this, LogInActivity.class)); finish();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "", Snackbar.LENGTH_LONG)
                        .setAction("", null).show();
            }
        });*/
        /*displayFragment(R.id.nav_sanction);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        ((Init) this.getApplicationContext()).setCi_user(getIntent().getStringExtra("id_ci"));

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SanctionFragment(), "Nueva Sanción");
        adapter.addFragment(new ProfileFragment(), "Lista de Sanciones");
        viewPager.setAdapter(adapter);
    }

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (!viewIsAtHome) {
            displayFragment(R.id.nav_sanction);
        } else {
            moveTaskToBack(true);
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

/*action_async*/
        switch (item.getItemId()) {
            case R.id.action_clear_list:
                ProfileFragment.refreshList();
                return true;
            case R.id.action_log_out:
                Log.d("MainActivity","Click Log Out");
                startActivity(new Intent(this, LogInActivity.class).putExtra("log_out", "log_out")); finish();
                return true;
        }
        /*if (id == R.id.action_log_out) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    /*@SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        displayFragment(item.getItemId());
        return true;
    }

    public void displayFragment(int fragmentId) {

        Fragment fragment = null;
        String titleBar = getString(R.string.app_name);

        switch (fragmentId) {

            case R.id.nav_sanction:
                fragment = new SanctionFragment();
                titleBar = getString(R.string.tmn_sanction);
                viewIsAtHome = true;
                break;

            case R.id.nav_merit:
                fragment = new MeritFragment();
                titleBar = getString(R.string.tmn_merit);
                viewIsAtHome = false;
                break;

            case R.id.nav_profile:
                fragment = new ProfileFragment();
                titleBar = getString(R.string.tmn_profile);
                viewIsAtHome = false;
                break;
        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titleBar);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }*/

}
