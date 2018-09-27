package com.example.vonkdothuser11.meanings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import java.io.IOException;

/**
 * Created by Vonkdoth User 11 on 7/15/2016.
 */
public class AndroidThread extends ActionBarActivity {
    int n=0;
    DBHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread thread = new Thread() {
            public void run() {
                try {
                    if(n==0){
                        Thread.sleep(1*1);
                        finish();
                        dbhelper = new DBHelper(getApplication());
                        try {
                            dbhelper.createDataBase();
                        } catch (IOException e) {
                            dbhelper.openDatabase();
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        dbhelper.close();
                        // quesList=dbhelper.getData();
                        // loadQuestions();
                        // shuffleJsonArray();


                        // Log.d("",""+Level.Level);
                        n++;
                        //Log.d("",""+creation);
                    }
                    Intent intent = new Intent(AndroidThread.this, ListMe.class);
                    AndroidThread.this.startActivity(intent);
                } catch (Exception e) {
                }
            }
        };
        thread.start();

    }
}
