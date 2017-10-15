package com.vaidoos.guitorio.badhanpstu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnLogin;
    TextView fade_in;

    private MyFirebaseInstanceIDService MyFirebaseInstanceIDService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("BADHAN,PSTU");


        fade_in = (TextView) findViewById(R.id.badhon_text);
        fade_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = (ImageView) findViewById(R.id.badhon);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.fade);
                image.startAnimation(animation);
                Toast.makeText(getApplicationContext(),"BADHAN,PSTU", Toast.LENGTH_SHORT).show();
            }
        });



        /*Intent intent = new Intent(this,DonorSearch.class);
        startActivity(intent);*/

        btnLogin = (Button) findViewById(R.id.btnLoginActivity);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.developer) {
            Toast.makeText(MainActivity.this, "Developer Site", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(MainActivity.this, Developer.class);
            startActivity(a);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.comittee) {

            Toast.makeText(MainActivity.this, "বর্তমান কমিটি",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, Comittee.class);
            startActivity(i);

        } else if (id == R.id.badhon_campus) {

            Toast.makeText(MainActivity.this,"বাঁধনের ইতিকথা",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, Badhon.class);
            startActivity(i);

        } else if (id == R.id.campus) {
            Toast.makeText(MainActivity.this, "পবিপ্রবি ক্যাম্পাস",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, PSTU.class);
            startActivity(i);

        } else if (id == R.id.contact) {
            Toast.makeText(MainActivity.this, "Please Contact with Us",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, Contact.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
