package global.nusantara.ngosis.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import global.nusantara.ngosis.fragment.TabMonitoringSiswaFragment;
import global.nusantara.ngosis.fragment.ProfilSiswaFragment;
import global.nusantara.ngosis.R;

public class MainGuruActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_guru);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.flContent, new ProfilSiswaFragment());
        tx.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_kepala_sekolah, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass = ProfilSiswaFragment.class;
        if (id == R.id.nav_jadwal_mengajar) {
            fragmentClass = ProfilSiswaFragment.class;
        } else if (id == R.id.nav_absensi_siswa) {
            fragmentClass = ProfilSiswaFragment.class;

        } else if (id == R.id.nav_upload_materi) {
            fragmentClass = ProfilSiswaFragment.class;

        } else if (id == R.id.nav_input_nilai_tugas) {
            fragmentClass = ProfilSiswaFragment.class;

        } else if (id == R.id.nav_input_nilai_siswa) {
            fragmentClass = ProfilSiswaFragment.class;

        } else if (id == R.id.nav_edit_profil) {
            fragmentClass = ProfilSiswaFragment.class;

        }else if (id == R.id.nav_monitoring_siswa) {
            fragmentClass = TabMonitoringSiswaFragment.class;

        } else if (id == R.id.nav_send_broadcast_message) {
            fragmentClass = ProfilSiswaFragment.class;

        } else if (id == R.id.nav_view_broadcast_message) {
            fragmentClass = ProfilSiswaFragment.class;

        } else if (id == R.id.nav_logout) {
            fragmentClass = ProfilSiswaFragment.class;

        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        item.setChecked(true);
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
