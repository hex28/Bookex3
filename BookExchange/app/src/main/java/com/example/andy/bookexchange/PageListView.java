package com.example.andy.bookexchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PageListView extends AppCompatActivity {

    private ListView pageListView;
    private DatabaseReference mDatabase;
    private String mbook_key = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_list_view);

        pageListView = (ListView) findViewById(R.id.pageList);

        mbook_key = getIntent().getExtras().getString("Books_id");
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Books");
        Toast.makeText(PageListView.this, mbook_key, Toast.LENGTH_LONG).show();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent readContent = new Intent(PageListView.this, reading.class);
                readContent.putExtra("books_id", mbook_key);
                startActivity(readContent);


            }
        });

        final FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                android.R.layout.simple_dropdown_item_1line,
                mDatabase.child(mbook_key).child("pages")
        ) {
            @Override
            protected void populateView(View v, String model, int position) {

                final String content_key = getRef(position).getKey();


            }
        };


        pageListView.setAdapter(firebaseListAdapter);


    }
}
