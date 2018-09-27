package com.example.vonkdothuser11.meanings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Vonkdoth User 11 on 7/15/2016.
 */
public class ListMe extends ActionBarActivity{
    public static JSONArray jsonArray=null;
    DBHelper dbHelper;
    ArrayList<HashMap<String,String>> Arraylist;
    ListAdapter listAdapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lisiview);
        list = (ListView) findViewById(R.id.lvExp);
        dbHelper = new DBHelper(getApplicationContext());
        jsonArray = dbHelper.getData();
        Log.d("gsdufjf", "" + jsonArray);
        Arraylist = new ArrayList<HashMap<String, String>>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = null;
            try {
                object = jsonArray.getJSONObject(i);
                String Ename = object.getString("wordname");
                String mne = object.getString("mnemonic");
                String mea = object.getString("meaning");
                HashMap<String, String> Hmap = new HashMap<>();
                Hmap.put("Ename", Ename);
                Hmap.put("Mne",mne);
                Hmap.put("Mea",mea);

                Arraylist.add(Hmap);
                listAdapter = new SimpleAdapter(ListMe.this, Arraylist, R.layout.list_item, new String[]{"Ename"}, new int[]{R.id.elist});
                list.setAdapter(listAdapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        HashMap<String,String> value=(HashMap<String, String>) parent.getItemAtPosition(position);
                        Log.d("xzfzdxgfd",""+value);
                        Intent in=new Intent(getApplicationContext(),Detail.class);
                        in.putExtra("Value",value);
                        startActivity(in);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }}
