package com.example.databasefragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class about_page extends AppCompatActivity implements View.OnClickListener {

    private String aboutText;
    private TextView field;
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;
    private Intent back;
    private Intent goTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        field = findViewById(R.id.textfield);
        buildText();
    }

    private void buildText() {
        is = getResources().openRawResource(R.raw.aboutme);
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        String st, all = "";

        try {
            while ((st = br.readLine()) != null)
                all += st + "\n";
            br.close();
        } catch (IOException e) {
            Toast.makeText(this, "Erorr", Toast.LENGTH_SHORT).show();
        }
        field.setText(all);
    }

    @Override
    public void onClick(View view) {
        back = new Intent(this, MainActivity.class);
        startActivity(back);
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
            goTo = new Intent(this, MainActivity.class);
            startActivity(goTo);
            return true;

        }
        else if (id == R.id.search) {
            Toast.makeText(this, "Search Button", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.about) {
            Toast.makeText(this, "about Button", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.view_data) {
            Toast.makeText(this, "about Button", Toast.LENGTH_SHORT).show();
            goTo = new Intent(this, ViewDataActivity.class);
            startActivity(goTo);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}