package com.example.np.halamooz_v1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v4.widget.DrawerLayout;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.np.halamooz_v1.model.User;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private View header;
    private Button btnSign;
    private TextView txtName;
    private User user;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);



//        txt=findViewById(R.id.activityMain_status);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        nvDrawer = (NavigationView) findViewById(R.id.nvView);
//        setupDrawerContent(nvDrawer);
//
//        drawerToggle = setupDrawerToggle();
//        // Tie DrawerLayout events to the ActionBarToggle
//        mDrawer.addDrawerListener(drawerToggle);
//
//        header=nvDrawer.getHeaderView(0);
//        btnSign=header.findViewById(R.id.btnSign);
//        txtName=header.findViewById(R.id.txtName);
//        btnSign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(intent);
//            }
//        });



       // View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);
// We can now look up items within the header if needed
        //ImageView ivHeaderPhoto = headerLayout.findViewById(R.id.imageView);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void load() {
        txt=findViewById(R.id.activityMain_status);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();
        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        header=nvDrawer.getHeaderView(0);
        btnSign=header.findViewById(R.id.btnSign);
        txtName=header.findViewById(R.id.txtName);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                mDrawer.openDrawer(GravityCompat.START);
//                return true;
//        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                Toast.makeText(MainActivity.this,"first item",Toast.LENGTH_SHORT).show();
               // fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_second_fragment:
                Toast.makeText(MainActivity.this,"first item",Toast.LENGTH_SHORT).show();
               // fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                Toast.makeText(MainActivity.this,"first item",Toast.LENGTH_SHORT).show();
               // fragmentClass = ThirdFragment.class;
                break;
            default:
                Toast.makeText(MainActivity.this,"first item",Toast.LENGTH_SHORT).show();
               // fragmentClass = FirstFragment.class;
        }
        try {
           // fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.app_name,  R.string.app_name);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI(user);
    }
    private void updateUI(User user){
        if(user.isLogin){
            btnSign.setVisibility(View.GONE);
            txtName.setVisibility(View.VISIBLE);
            txtName.setText(user.getName()+" "+user.getFamily());
        }else {
            btnSign.setVisibility(View.VISIBLE);
            txtName.setVisibility(View.GONE);
        }
    }



}
