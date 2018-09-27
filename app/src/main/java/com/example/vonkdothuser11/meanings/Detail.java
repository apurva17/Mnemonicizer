package com.example.vonkdothuser11.meanings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Vonkdoth User 11 on 8/13/2016.
 */
public class Detail extends ActionBarActivity implements View.OnTouchListener {
    HashMap<String, String> values = new HashMap<>();
    static String name, mne,mea;
    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail);

        LinearLayout layout= (LinearLayout)findViewById(R.id.LinearLayout1);
        layout.setOnTouchListener( this);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);

        values = (HashMap<String, String>) getIntent().getSerializableExtra("Value");
        Log.d("array", "" + values);
        name = values.get("Ename");
        mne = values.get("Mne");
        mea = values.get("Mea");
        t1.setText(name);
        t2.setText(mne);
        t3.setText(mea);


    }
    public boolean onTouch(View v, MotionEvent event) {
        Intent intent = new Intent(this, ListMe.class);
        startActivity(intent);
        return true;
    }
}
