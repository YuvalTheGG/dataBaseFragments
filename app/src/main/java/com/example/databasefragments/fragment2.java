package com.example.databasefragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class fragment2 extends Fragment {

    DatabaseHelper myDb;
    EditText name, surename, email, etId, etPhone;
    Button btnInsert;
    Button btnView, btnUpdate, btnDelete;
    Button btnBack;
    Intent backTo, goTo;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_fragment2, container, false);

        myDb = new DatabaseHelper(getActivity());

        name = rootView.findViewById(R.id.FNameEt);
        surename = rootView.findViewById(R.id.LNameEt);
        email = rootView.findViewById(R.id.EmailEt);
        etId = rootView.findViewById(R.id.IdEt);
        etPhone = rootView.findViewById(R.id.PhoneEt);

        btnInsert = rootView.findViewById(R.id.insert_btn);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(
                        etId.getText().toString(),
                        name.getText().toString(),
                        surename.getText().toString(),
                        email.getText().toString(),
                        etPhone.getText().toString());

                if (isInserted == true) {
                    Toast.makeText(getActivity(), "Data Inserted", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getActivity(), "Data not Inserted", Toast.LENGTH_LONG).show();
                name.setText("");
                surename.setText("");
                email.setText("");
                etPhone.setText("");
                etId.setText("");
            }
        });

//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Cursor res = myDb.getAllData();
//                if (res.getCount() == 0) {
//                    //show message
//                    showData("Error", "No Data Found");
//                    return;
//                }
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()) {
//                    buffer.append("ID: " + res.getString(0) + "\n");
//                    buffer.append("NAME: " + res.getString(1) + "\n");
//                    buffer.append("SURNAME: " + res.getString(2) + "\n");
//                    buffer.append("EMAIL: " + res.getString(3) + "\n\n");
//                }
//
//                showData("Data", buffer.toString());
//            }
//        });

//        btnView = rootView.findViewById(R.id.view_btn);
//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goTo = new Intent(getActivity(), ViewDataActivity.class);
//                startActivity(goTo);
//            }
//
//        });

        btnUpdate = rootView.findViewById(R.id.update_btn);
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isUpdated = myDb.updateData(
                        etId.getText().toString(),
                        name.getText().toString(),
                        surename.getText().toString(),
                        email.getText().toString(),
                        etPhone.getText().toString());
                if (isUpdated == true) {
                    Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(), "Data Not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete = rootView.findViewById(R.id.delete_btn);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myDb.deleteData(etId.getText().toString()) > 0) {
                    Toast.makeText(getActivity(), "Data Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Data Not Deleted", Toast.LENGTH_LONG).show();
                }

                etId.setText("");
            }
        });

        btnBack = (Button) rootView.findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backTo = new Intent(getActivity(), MainActivity.class);
                startActivity(backTo);
            }
        });

        return rootView;

    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getActivity().getMenuInflater().inflate(R.menu.total, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.home) {
//            Toast.makeText(getActivity(), "Home Screen Button", Toast.LENGTH_SHORT).show();
//            goTo = new Intent(getActivity(), MainActivity.class);
//            startActivity(goTo);
//            return true;
//
//        }
//        else if (id == R.id.search) {
//            Toast.makeText(getActivity(), "Search Button", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        else if (id == R.id.about) {
//            Toast.makeText(getActivity(), "about Button", Toast.LENGTH_SHORT).show();
//            goTo = new Intent(getActivity(), aboutPage.class);
//            startActivity(goTo);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
