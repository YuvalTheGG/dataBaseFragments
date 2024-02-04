package com.example.databasefragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewDataActivity extends AppCompatActivity {

    private Button btnView, btnBack;
    private DatabaseHelper myDb;
    private EditText id;
    private Intent goTo, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        myDb = new DatabaseHelper(this);
        btnView = findViewById(R.id.view_data);
        id = findViewById(R.id.idTest);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getDataById(id.getText().toString());
                if (res.getCount() == 0) {
                    //show message
                    showData("Error", "No Data Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID: " + res.getString(0) + "\n");
                    buffer.append("NAME: " + res.getString(1) + "\n");
                    buffer.append("SURNAME: " + res.getString(2) + "\n");
                    buffer.append("EMAIL: " + res.getString(3) + "\n");
                    buffer.append("Phone: " + res.getString(4) + "\n\n");
                }
                showData("Data", buffer.toString());
            }
        });

        btnBack = findViewById(R.id.back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = new Intent(ViewDataActivity.this, MainActivity.class);
                startActivity(back);
            }
        });
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
            goTo = new Intent(this, about_page.class);
            startActivity(goTo);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void showData(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}