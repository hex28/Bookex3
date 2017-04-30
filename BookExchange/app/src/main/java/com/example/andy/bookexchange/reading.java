package com.example.andy.bookexchange;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class reading extends AppCompatActivity {


    private String contentKey;

    private DatabaseReference mDatabase;

    private TextView mContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        PageFragment pageFragment = new PageFragment();
        ft.add(R.id.fragment, pageFragment);
        ft.commit();


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Books");

        contentKey = getIntent().getExtras().getString("books_id");

        mContents = (TextView) findViewById(R.id.pageContent);

        Toast.makeText(reading.this, contentKey, Toast.LENGTH_LONG).show();

        mDatabase.child(contentKey).child("Content").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                String pageContent = (String) dataSnapshot.child("page1").getValue();

                mContents.setText(pageContent);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        

    }
}
