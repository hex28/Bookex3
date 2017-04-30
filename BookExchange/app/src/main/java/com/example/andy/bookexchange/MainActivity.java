package com.example.andy.bookexchange;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mBookList;

    private DatabaseReference mDatabase;

    private Toolbar toolbar;

    private static String url = "http://api.androidhive.info/facebook/firebase_analytics.html";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BookExchange");


        //RecyclerView
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Books");
        mDatabase.keepSynced(true);

        mBookList = (RecyclerView) findViewById(R.id.book_list);
        mBookList.setHasFixedSize(true);
        //mBookList.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mBookList.setLayoutManager(mLayoutManager);


        



    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Book, BookViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Book, BookViewHolder>(

                Book.class,
                R.layout.book_row,
                BookViewHolder.class,
                mDatabase



        ) {
            // Populate View Holder starts here

            @Override
            protected void populateViewHolder(final BookViewHolder viewHolder, Book model, int position) {

                final String book_key = getRef(position).getKey();
                
                viewHolder.setTitle(model.getTitle());
                viewHolder.setAuthor(model.getAuthor());
                viewHolder.setDesc(model.getDesc());
                
               /* viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        
                        Intent readContentIntent = new Intent(MainActivity.this, reading.class);
                        readContentIntent.putExtra("book_id", book_key);
                        startActivity(readContentIntent);
                    }
                });*/

               viewHolder.readContent.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       Intent openPageList = new Intent(MainActivity.this, PageListView.class);
                       openPageList.putExtra("Books_id", book_key);
                       startActivity(openPageList);




                   }
               });

                /*Original Comment Section if Needed!
                viewHolder.mComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                        View mCview = getLayoutInflater().inflate(R.layout.comment_box, null);
                        EditText mName = (EditText) mCview.findViewById(R.id.commentName);
                        EditText mComment = (EditText) mCview.findViewById(R.id.commentDialog);

                        mBuilder.setView(mCview);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();

                    }
                });*/

                viewHolder.mComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                        Intent fbComments = new Intent(MainActivity.this, fbCommentActivity.class);
                        fbComments.putExtra("url","http://www.androidhive.info/2016/06/android-firebase-integrate- analytics/");
                        startActivity(fbComments);


                        //View mCview = getLayoutInflater().inflate(R.layout.activity_fb_comment, null);



                        //mBuilder.setView(fbComments);
                        AlertDialog dialog = mBuilder.create();
                        dialog.show();



                    }
                });




            }

        };

        mBookList.setAdapter(firebaseRecyclerAdapter);


    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        View mView;

        TextView readContent;

        TextView mComment;

        WebView webView;


        public BookViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            readContent = (TextView) mView.findViewById(R.id.readContent);

            mComment = (TextView) mView.findViewById(R.id.comment);


        }

        public void setTitle(String title){

            TextView book_title = (TextView) mView.findViewById(R.id.book_title);
            book_title.setText(title);

        }

        public void setAuthor(String author){

            TextView book_author = (TextView) mView.findViewById(R.id.book_author);
            book_author.setText(author);
        }

        public void setDesc(String desc){

            TextView book_desc = (TextView) mView.findViewById(R.id.book_desc);
            book_desc.setText(desc);
        }
        
        
    }
}
// Do not edit top codes. RecyclerView