package com.example.priya.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListviewAdapter listviewAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        listView= (ListView)findViewById(R.id.listview);
        listviewAdapter=new ListviewAdapter(DisplayListView.this,R.layout.row_layout);
        listView.setAdapter(listviewAdapter);
        json_string=getIntent().getExtras().getString("Json_data");
        try {

            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("items");
            int count=0;
            String name;
            while(count<jsonArray.length()){

                JSONObject JO=jsonArray.getJSONObject(count);
                name=JO.getString("name");

                Display display =new Display(name);

                listviewAdapter.add(display);

                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
