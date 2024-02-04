package com.example.databasefragments;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private Intent goTo;
    ViewPager2 viewPager;
    MyViewPagerAdapter myAdapter;
//    TabLayout tabLayout;
    private boolean mIsBound = false;
    public static boolean isPlaying = true;
    public static MusicService mServ;

    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this, MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        doBindService();

        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.total, menu);
        //MenuItem Item = menu.findItem(R.id.home);
        //MenuItem menuItem = menu.findItem(R.id.search);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            Toast.makeText(this, "Home Screen Button", Toast.LENGTH_SHORT).show();
            return true;

        }
        else if (id == R.id.search) {
            Toast.makeText(this, "Search Button", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.about) {
            Toast.makeText(this, "about Button", Toast.LENGTH_SHORT).show();
            goTo = new Intent(this, about_page.class);
            startActivity(goTo);
            return true;
        }
        else if (id == R.id.view_data) {
            Toast.makeText(this, "Logout Button", Toast.LENGTH_SHORT).show();
            goTo = new Intent(this, ViewDataActivity.class);
            startActivity(goTo);
            return true;
        }
        else if (id == R.id.btnMute) {
            if (MainActivity.isPlaying) {
                MainActivity.mServ.pauseMusic();
                item.setTitle("Unmute");
            } else {
                MainActivity.mServ.resumeMusic();
                item.setTitle("Mute");
            }
            MainActivity.isPlaying = !MainActivity.isPlaying;
        }


        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
        goTo = new Intent(this, MainActivity2.class);
        startActivity(goTo);


    }
}